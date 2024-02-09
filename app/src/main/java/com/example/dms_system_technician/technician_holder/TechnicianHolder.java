package com.example.dms_system_technician.technician_holder;

import com.example.dms_system_technician.dto.TechnicianDto;

public class TechnicianHolder extends TechnicianDto {

    private String jwtToken;

   private static TechnicianHolder instance;

    public static TechnicianHolder getInstance() {

        if(instance==null){
            instance = new TechnicianHolder(4,
                    4,
                    "Aubrey", "Mashaba",
                    "123abc@gmail.com","+27760794703",
                    2,4,
                    "Water and Sanitation",
                    "Plumber",""
                    );
        }


        return instance;
    }

    public static void build(TechnicianHolder instance) {
        TechnicianHolder.instance = instance;
    }

    public TechnicianHolder() {
    }

    public TechnicianHolder(long userId, long technicianId, String firstname, String lastname, String email, String cellNo, long deptID, long specId, String deptName, String specName, String jwtToken) {
        super(userId, technicianId, firstname, lastname, email, cellNo, deptID, specId, deptName, specName);
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
