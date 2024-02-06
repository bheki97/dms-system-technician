package com.example.dms_system_technician.dto;

public class TechnicianDto {
    private long userId;
    private long technicianId;
    private String firstname;
    private String lastname;
    private String email;
    private String cellNo;
    private long deptID;
    private long specId;
    private String deptName;
    private String specName;

    public TechnicianDto() {
    }

    public TechnicianDto(long userId, long technicianId, String firstname, String lastname, String email, String cellNo, long deptID, long specId, String deptName, String specName) {
        this.userId = userId;
        this.technicianId = technicianId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.cellNo = cellNo;
        this.deptID = deptID;
        this.specId = specId;
        this.deptName = deptName;
        this.specName = specName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(long technicianId) {
        this.technicianId = technicianId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public long getDeptID() {
        return deptID;
    }

    public void setDeptID(long deptID) {
        this.deptID = deptID;
    }

    public long getSpecId() {
        return specId;
    }

    public void setSpecId(long specId) {
        this.specId = specId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
