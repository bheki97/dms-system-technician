package com.example.dms_system_technician.retrofit;


import com.example.dms_system_technician.dto.DisasterDto;
import com.example.dms_system_technician.dto.LoginDto;
import com.example.dms_system_technician.dto.ResolvedDisasterDto;
import com.example.dms_system_technician.dto.TechnicianDto;
import com.example.dms_system_technician.technician_holder.TechnicianHolder;
import com.example.dms_system_technician.userdetails.UserDetailsHolder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DmsServerAPI {
    String PREFIX = "/api";

    //login
    @POST("/auth")
    Call<UserDetailsHolder> login(@Body LoginDto dto);

    @GET(PREFIX+"/technician/user/{userId}")
    Call<TechnicianHolder> getTechnicianByUserId(@Path("userId") long userId);


    //get All Incomplete
    @GET(PREFIX+"/disaster/incomplete/{technician}")
    Call<DisasterDto[]> getAllInCompeteDisasters(@Path("technician") long technicianId);

    //get Complete Disaster
    @GET(PREFIX+"/disaster/complete/{technician}")
    Call<ResolvedDisasterDto[]> getAllCompeteDisasters(@Path("technician") long technicianId);

    //attend disaster
    @GET(PREFIX+"/disaster/attend/{reportId}")
    Call<Boolean> attendDisaster(@Path("reportId") long reportId);

    //complete disaster
    @GET(PREFIX+"/disaster/resolve/{reportId}")
    Call<Boolean> completeDisaster(@Path("reportId") long reportId);







}
