package com.example.dms_system_technician;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dms_system_technician.databinding.ActivityLoginBinding;
import com.example.dms_system_technician.dto.LoginDto;
import com.example.dms_system_technician.exception.UiException;
import com.example.dms_system_technician.retrofit.DmsServerAPI;
import com.example.dms_system_technician.retrofit.RetrofitService;
import com.example.dms_system_technician.userdetails.UserDetailsHolder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private DmsServerAPI dmsServerAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        dmsServerAPI = RetrofitService.getInstance().getRetrofit().create(DmsServerAPI.class);

        setContentView(binding.getRoot());

        addOnClickForLoginBtn();

    }

    private void addOnClickForLoginBtn() {
        binding.loginBtn.setOnClickListener( v->{

            LoginDto dto;
            try{
                dto = validateLoginDetails();
            }catch(UiException exc){
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            sendLoginRequest(dto);


        });
    }

    private void sendLoginRequest(LoginDto dto) {
        dmsServerAPI.login(dto).enqueue(new Callback<UserDetailsHolder>() {
            @Override
            public void onResponse(Call<UserDetailsHolder> call, Response<UserDetailsHolder> response) {

                if(response.code()==200){
                    if(response.body().getUserRole()=="technician"){
                        UserDetailsHolder.build(response.body());
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this,"You are not authorized to use this app",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    try {
                        Toast.makeText(LoginActivity.this,response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(LoginActivity.this,"Error Occurred, try again",Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<UserDetailsHolder> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Could not connect to server!!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private LoginDto validateLoginDetails()throws UiException {
        LoginDto dto = new LoginDto();
        String data;
        if((data = binding.emailTxtEdit.getText().toString()).isEmpty()){
            throw new UiException("Email Field is empty!!");
        }

        dto.setUsername(data);
        if((data = binding.pwdTxtEdit.getText().toString()).isEmpty()){
            throw new UiException("Password Field is empty!!");
        }
        dto.setPassword(data);
        return dto;
    }
}