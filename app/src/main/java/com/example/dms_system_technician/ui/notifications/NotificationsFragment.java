package com.example.dms_system_technician.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dms_system_technician.databinding.FragmentNotificationsBinding;
import com.example.dms_system_technician.technician_holder.TechnicianHolder;


public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel homeViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setProfileDetails();

    }

    private void setProfileDetails() {
        TechnicianHolder holder = TechnicianHolder.getInstance();
        setReporterName(holder.getFirstname(),holder.getLastname());
        binding.emailTxt.setText(holder.getEmail());
        binding.cellNoTxt.setText(holder.getCellNo());
        binding.DeptNameTxt.setText(holder.getDeptName());
        binding.specNameTxt.setText(holder.getSpecName());
    }

    private void setReporterName(String name,String surname) {

        String username = convertToInitCap(name+" "+surname);
        binding.reporterNameTxt.setText(username);
    }

    private String convertToInitCap(String name){
        String names[] = name.split(" ");
        name = "";
        String val;
        for(int i=0; i<names.length;i++){
            val = names[i];
            val = val.toLowerCase();
            val  = Character.toUpperCase(val.charAt(0))+val.substring(1);
            name  += val +" ";
        }
        return name.trim();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}