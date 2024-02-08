package com.example.dms_system_technician;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.example.dms_system_technician.databinding.ActivityActiveDisasterBinding;
import com.example.dms_system_technician.dto.DisasterDto;
import com.example.dms_system_technician.dto.ReporterDto;
import com.example.dms_system_technician.enums.DisasterType;
import com.example.dms_system_technician.recycler.active_disaster.ActiveDisasterHolder;
import com.example.dms_system_technician.retrofit.DmsServerAPI;
import com.example.dms_system_technician.retrofit.RetrofitService;
import com.example.dms_system_technician.technician_holder.TechnicianHolder;

import java.sql.Timestamp;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveDisasterActivity extends AppCompatActivity {

    private ActivityActiveDisasterBinding binding;
    private DisasterDto dtoHolder;
    private DmsServerAPI dmsServerAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityActiveDisasterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dmsServerAPI = RetrofitService.getInstance().getRetrofit().create(DmsServerAPI.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        this.dtoHolder = DisasterDto.getInstance();
        setUpTheFields();
        toggleAttendOrAttendBtn();

    }

    private void toggleAttendOrAttendBtn() {

        if(dtoHolder.getReportDto().getTechnicianAttendDate()==null){
            binding.attendOrCompleteBtn.setText("ATTEND");
            setToAttendDisaster();
        }else {
            binding.attendOrCompleteBtn.setText("COMPLETE");
            setToCompleteDisaster();
        }


    }

    private void setToCompleteDisaster() {
        binding.attendOrCompleteBtn.setOnClickListener(v ->{
            dmsServerAPI.completeDisaster(this.dtoHolder.getReportDto().getDisasterReportId())
                    .enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.code()==200 && response.body()){
                                binding.attendOrCompleteBtn.setClickable(false);
                                dtoHolder.getReportDto().setTechnicianAttendDate(new Timestamp(System.currentTimeMillis()));

                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {

                        }
                    });
        });
    }

    private void setToAttendDisaster() {
        binding.attendOrCompleteBtn.setOnClickListener(v ->{
            dmsServerAPI.attendDisaster(this.dtoHolder.getReportDto().getDisasterReportId())
                    .enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.code()==200 && response.body()){
                                binding.attendOrCompleteBtn.setText("COMPLETE");

                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {

                        }
                    });
        });
    }

    private void setUpTheFields() {
        setReportDate(dtoHolder.getReportDto().getReportDate());
        setDelegationDate(dtoHolder.getReportDto().getDelegationDate());
        setDisasterType(dtoHolder.getType());
        setDisasterImage(dtoHolder.getImgFileContent());


        ReporterDto reporter = dtoHolder.getReporter();
        setReporterName(reporter.getFirstname(),reporter.getLastname());
        setReporterCellNo(reporter.getCellNo());
        binding.emailTxt.setText(dtoHolder.getReporter().getEmail());


    }

    private void setReporterCellNo(String cellNo) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(cellNo.substring(0,3));
        stringBuilder.append(" ");

        stringBuilder.append(cellNo.substring(3,6));
        stringBuilder.append(" ");
        stringBuilder.append(cellNo.substring(6));

    }

    private void setReporterName(String name,String surname) {

        String username = convertToInitCap(name+" "+surname);
        binding.reporterNameTxt.setText(username);
    }

    private void setDisasterImage(String imgFileContent) {
        byte[] bytArr = Base64.decode(imgFileContent,Base64.NO_WRAP);
        Bitmap bitMap = BitmapFactory.decodeByteArray(bytArr,0,bytArr.length);
        binding.disasterImg.setImageBitmap(bitMap);
    }

    private void setDisasterType(DisasterType type) {
        String strType  = type.toString().replace("_", " ");

        binding.disasterTypeTxt.setText(strType);
    }

    private void setReportDate(Timestamp reportDate) {
        Date date = new Date(reportDate.getTime());
        binding.reportDateTxt.setText(ActiveDisasterHolder.DATE_FORMATTER.format(date));
    }
    private void setDelegationDate(Timestamp delegationDate) {
        Date date = new Date(delegationDate.getTime());
        binding.delegationDateTxt.setText(ActiveDisasterHolder.DATE_FORMATTER.format(date));
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
}