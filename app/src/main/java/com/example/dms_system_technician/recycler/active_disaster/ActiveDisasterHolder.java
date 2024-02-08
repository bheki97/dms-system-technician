package com.example.dms_system_technician.recycler.active_disaster;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dms_system_technician.ActiveDisasterActivity;
import com.example.dms_system_technician.databinding.HolderActiveDisasterBinding;
import com.example.dms_system_technician.R;
import com.example.dms_system_technician.dto.DisasterDto;
import com.example.dms_system_technician.enums.DisasterType;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActiveDisasterHolder extends RecyclerView.ViewHolder {

    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());


    private HolderActiveDisasterBinding binding;
    private DisasterDto dtoHolder;

    public ActiveDisasterHolder(@NonNull HolderActiveDisasterBinding binding) {
        super(binding.getRoot());
        this.binding  = binding;
    }

    public void bind(DisasterDto dto){
        this.dtoHolder = dto;
        setReportDate(dto.getReportDto().getReportDate());
        setStatus(dto.getReportDto().getTechnicianAttendDate());
        setDisasterType(dto.getType());
        setDisasterImage(dto.getImgFileContent());
        setViewDisaster();
    }

    private void setViewDisaster() {
        binding.viewTxtBtn.setOnClickListener( v ->{
            DisasterDto.build(dtoHolder);
            binding.getRoot().getContext().startActivity(
                    new Intent(binding.getRoot().getContext(), ActiveDisasterActivity.class));
        });
    }

    private void setStatus(Timestamp technicianAttendDate) {

        if(technicianAttendDate==null){
            binding.statusImg.setImageDrawable(
                    ResourcesCompat.getDrawable(binding.getRoot().getResources(), R.drawable.outline_dangerous_24,null)
            );
            binding.statusTxt.setText("UNATTENDED");
            binding.statusTxt.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(),R.color.unattended));
        }else{
            binding.statusImg.setImageDrawable(
                    ResourcesCompat.getDrawable(binding.getRoot().getResources(), R.drawable.outline_run_circle_24,null)
            );
            binding.statusTxt.setText("ATTENDING");
            binding.statusTxt.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(),R.color.attending));
        }
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
        binding.reportDateTxt.setText(DATE_FORMATTER.format(date));
    }
}
