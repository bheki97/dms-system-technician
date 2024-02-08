package com.example.dms_system_technician.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dms_system_technician.databinding.FragmentDashboardBinding;
import com.example.dms_system_technician.dto.ResolvedDisasterDto;
import com.example.dms_system_technician.recycler.resolved_disaster.ResolvedDisasterRecycler;
import com.example.dms_system_technician.retrofit.DmsServerAPI;
import com.example.dms_system_technician.retrofit.RetrofitService;
import com.example.dms_system_technician.technician_holder.TechnicianHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DmsServerAPI dmsServerAPI;
    private ResolvedDisasterRecycler resolvedDisasterRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dmsServerAPI = RetrofitService.getInstance().getRetrofit().create(DmsServerAPI.class);


        binding.historyDisastersRecycler.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        resolvedDisasterRecycler = new ResolvedDisasterRecycler();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchAllCompleteDisasters();
    }

    private void fetchAllCompleteDisasters() {
        System.out.println("Fetching disasters for the TechnicianId: "+TechnicianHolder.getInstance().getTechnicianId());
        dmsServerAPI.getAllCompeteDisasters(TechnicianHolder.getInstance().getTechnicianId())
                .enqueue(new Callback<ResolvedDisasterDto[]>() {
                    @Override
                    public void onResponse(Call<ResolvedDisasterDto[]> call, Response<ResolvedDisasterDto[]> response) {

                        if(response.code()==200){

                            List<ResolvedDisasterDto> list = new ArrayList<>();
                            Collections.addAll(list, response.body());
                            resolvedDisasterRecycler.setResolvedDisasters(list);
                            binding.historyDisastersRecycler.setAdapter(resolvedDisasterRecycler);

                        }


                    }

                    @Override
                    public void onFailure(Call<ResolvedDisasterDto[]> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}