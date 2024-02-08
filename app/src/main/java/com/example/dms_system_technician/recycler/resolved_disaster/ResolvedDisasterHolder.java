package com.example.dms_system_technician.recycler.resolved_disaster;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dms_system_technician.databinding.HolderResolvedDisasterBinding;
import com.example.dms_system_technician.dto.ResolvedDisasterDto;
import com.example.dms_system_technician.enums.DisasterType;
import com.example.dms_system_technician.recycler.active_disaster.ActiveDisasterHolder;

import java.sql.Timestamp;
import java.util.Date;

public class ResolvedDisasterHolder extends RecyclerView.ViewHolder{

    private HolderResolvedDisasterBinding binding;
    private ResolvedDisasterDto dto;

    public ResolvedDisasterHolder(@NonNull HolderResolvedDisasterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ResolvedDisasterDto dto){
        setDisasterImage(dto.getImgContent());
        setDisasterType(dto.getType());
        setReportDate(dto.getReportDate());
        setCompleteDate(dto.getCompleteDate());


    }

    private void setDisasterImage(String imgFileContent) {
        byte[] bytArr = Base64.decode(imgFileContent,Base64.NO_WRAP);
        Bitmap bitMap = BitmapFactory.decodeByteArray(bytArr,0,bytArr.length);
        binding.disasterImg.setImageBitmap(bitMap);
    }

    private void setDisasterType(DisasterType type) {
        String strType;
        switch (type){
            case ABNORMAL_POWER_OUTAGE:
                strType = "Abnormal Power Outage";
                break;
            case POTHOLE:
                strType = "Pothole";
                break;
            case ELECTRIC_CABLE_THEFT:
                strType = "Electric Cable Theft";
                break;
            case CABBAGE_COLLECTION_DELAY:
                strType = "Cabbage Collection Delay";
                break;
            case PUBLIC_BUILDING_VANDALIZING:
                strType = "Public Building Vandalizing";
                break;
            case SEWAGE_BLOCK:
                strType = "Sewage Block";
                break;
            case WATER_PIPE_LEAK:
                strType = "Water pipe leak";
                break;
            default:
                strType = "Unknown";
        }
        binding.typeTxt.setText(strType);
    }

    private void setReportDate(Timestamp reportDate) {
        Date date = new Date(reportDate.getTime());
        binding.reportDateTxt.setText(ActiveDisasterHolder.DATE_FORMATTER.format(date));
    }
    private void setCompleteDate(Timestamp completeDate) {
        Date date = new Date(completeDate.getTime());
        binding.completeDateTxt.setText(ActiveDisasterHolder.DATE_FORMATTER.format(date));
    }
}
