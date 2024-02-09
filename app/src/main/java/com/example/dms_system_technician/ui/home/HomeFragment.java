package com.example.dms_system_technician.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dms_system_technician.databinding.FragmentHomeBinding;
import com.example.dms_system_technician.dto.DisasterDto;
import com.example.dms_system_technician.recycler.active_disaster.ActiveDisasterRecycler;
import com.example.dms_system_technician.retrofit.DmsServerAPI;
import com.example.dms_system_technician.retrofit.RetrofitService;
import com.example.dms_system_technician.technician_holder.TechnicianHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DmsServerAPI dmsServerAPI;
    private ActiveDisasterRecycler activeDisasterRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        dmsServerAPI = RetrofitService.getInstance().getRetrofit().create(DmsServerAPI.class);


        binding.activeDisastersRecycler.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        activeDisasterRecycler = new ActiveDisasterRecycler();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchAllActiveDisasters();

    }

    private void fetchAllActiveDisasters() {
        System.out.println("Fetching disasters for the TechnicianId: "+TechnicianHolder.getInstance().getTechnicianId());
        dmsServerAPI.getAllInCompeteDisasters(TechnicianHolder.getInstance().getTechnicianId())
                .enqueue(new Callback<DisasterDto[]>() {
                    @Override
                    public void onResponse(Call<DisasterDto[]> call, Response<DisasterDto[]> response) {

                        if(response.code()==200){

                            List<DisasterDto> list = new ArrayList<>();
                            System.out.println("Got All disasters: "+list.size());
                            Collections.addAll(list, response.body());

                            if(list.isEmpty()){
                                binding.nothingHereLayout.setVisibility(View.VISIBLE);
                            }

                            activeDisasterRecycler.setDisasters(list);
                            binding.activeDisastersRecycler.setAdapter(activeDisasterRecycler);


                        }


                    }

                    @Override
                    public void onFailure(Call<DisasterDto[]> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}