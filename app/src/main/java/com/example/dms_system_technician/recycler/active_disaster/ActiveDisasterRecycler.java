package com.example.dms_system_technician.recycler.active_disaster;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dms_system_technician.databinding.HolderActiveDisasterBinding;
import com.example.dms_system_technician.dto.DisasterDto;

import java.util.List;

public class ActiveDisasterRecycler extends RecyclerView.Adapter<ActiveDisasterHolder>{

    private List<DisasterDto> disasters;

    public ActiveDisasterRecycler() {
    }

    public ActiveDisasterRecycler(List<DisasterDto> disasters) {
        this.disasters = disasters;
    }

    @NonNull
    @Override
    public ActiveDisasterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HolderActiveDisasterBinding binding = HolderActiveDisasterBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );

        return new ActiveDisasterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveDisasterHolder holder, int position) {
        holder.bind(disasters.get(position));
    }

    @Override
    public int getItemCount() {
        return disasters==null? 0:disasters.size();
    }
}
