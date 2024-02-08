package com.example.dms_system_technician;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dms_system_technician.databinding.ActivityLoginBinding;
import com.example.dms_system_technician.dto.LoginDto;
import com.example.dms_system_technician.exception.UiException;
import com.example.dms_system_technician.technician_holder.TechnicianHolder;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        addOnClickForLoginBtn();

    }

    private void addOnClickForLoginBtn() {
        binding.loginBtn.setOnClickListener( v->{
            try{
                validateLoginDetails();
            }catch(UiException exc){
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

            LoginDto dto = new LoginDto();

//            sendLoginRequest(dto);

            TechnicianHolder.build(new TechnicianHolder(4,
                    4,
                    "Aubrey", "Mashaba",
                    "123abc@gmail.com","+760794703",
                    2,4,
                    "Water and Sanitation",
                    "Plumber",""
            ));

            startActivity(new Intent(this,MainActivity.class));
            finish();
        });
    }

    private void validateLoginDetails()throws UiException {
        if(binding.emailTxtEdit.getText().toString().isEmpty()){
            throw new UiException("Email Field is empty!!");
        }
        if(binding.pwdTxtEdit.getText().toString().isEmpty()){
            throw new UiException("Password Field is empty!!");
        }

    }
}