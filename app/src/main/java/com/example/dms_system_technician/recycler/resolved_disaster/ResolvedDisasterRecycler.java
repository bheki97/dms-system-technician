package com.example.dms_system_technician.recycler.resolved_disaster;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dms_system_technician.databinding.HolderResolvedDisasterBinding;
import com.example.dms_system_technician.dto.ResolvedDisasterDto;

import java.util.List;

public class ResolvedDisasterRecycler extends RecyclerView.Adapter<ResolvedDisasterHolder>{

    private List<ResolvedDisasterDto>resolvedDisasters;


    public ResolvedDisasterRecycler() {
    }

    public List<ResolvedDisasterDto> getResolvedDisasters() {
        return resolvedDisasters;
    }

    public void setResolvedDisasters(List<ResolvedDisasterDto> resolvedDisasters) {
        this.resolvedDisasters = resolvedDisasters;
    }

    public ResolvedDisasterRecycler(List<ResolvedDisasterDto> resolvedDisasters) {
        this.resolvedDisasters = resolvedDisasters;
    }

    @NonNull
    @Override
    public ResolvedDisasterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HolderResolvedDisasterBinding binding = HolderResolvedDisasterBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );

        return new ResolvedDisasterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResolvedDisasterHolder holder, int position) {
        holder.bind(resolvedDisasters.get(position));
    }

    @Override
    public int getItemCount() {
        return resolvedDisasters==null?0:resolvedDisasters.size();
    }
}
