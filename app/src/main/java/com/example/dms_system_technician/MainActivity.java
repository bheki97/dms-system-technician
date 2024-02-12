package com.example.dms_system_technician;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dms_system_technician.retrofit.DmsServerAPI;
import com.example.dms_system_technician.retrofit.RetrofitService;
import com.example.dms_system_technician.technician_holder.TechnicianHolder;
import com.example.dms_system_technician.userdetails.UserDetailsHolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dms_system_technician.databinding.ActivityMainBinding;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration mAppBarConfiguration;
    private DmsServerAPI dmsServerAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dmsServerAPI = RetrofitService.getInstance().getRetrofit().create(DmsServerAPI.class);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard)
                .build();



        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTechnicianDetails();
    }

    private void fetchTechnicianDetails() {
        dmsServerAPI.getTechnicianByUserId(UserDetailsHolder.getInstance().getUserId()).enqueue(
                new Callback<TechnicianHolder>() {
                    @Override
                    public void onResponse(Call<TechnicianHolder> call, Response<TechnicianHolder> response) {

                        if(response.code()==200){
                            TechnicianHolder holder = response.body();
                            holder.setJwtToken(UserDetailsHolder.getInstance().getJwtToken());
                            TechnicianHolder.build(holder);
                        }else{
                            try {
                                Toast.makeText(MainActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                Toast.makeText(MainActivity.this, "error occurred", Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        }


                    }

                    @Override
                    public void onFailure(Call<TechnicianHolder> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Could not connect to Server", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== R.id.logout) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}