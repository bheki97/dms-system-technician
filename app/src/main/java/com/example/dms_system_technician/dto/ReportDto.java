package com.example.dms_system_technician.dto;

import java.sql.Timestamp;

public class ReportDto {
    private long disasterReportId;
    private TechnicianDto technicianDto;
    private Timestamp reportDate;
    private Timestamp delegationDate;
    private Timestamp technicianAttendDate;
    private Timestamp completeDate;

    public long getDisasterReportId() {
        return disasterReportId;
    }

    public void setDisasterReportId(long disasterReportId) {
        this.disasterReportId = disasterReportId;
    }

    public TechnicianDto getTechnicianDto() {
        return technicianDto;
    }

    public void setTechnicianDto(TechnicianDto technicianDto) {
        this.technicianDto = technicianDto;
    }

    public Timestamp getReportDate() {
        return reportDate;
    }

    public void setReportDate(Timestamp reportDate) {
        this.reportDate = reportDate;
    }

    public Timestamp getDelegationDate() {
        return delegationDate;
    }

    public void setDelegationDate(Timestamp delegationDate) {
        this.delegationDate = delegationDate;
    }

    public Timestamp getTechnicianAttendDate() {
        return technicianAttendDate;
    }

    public void setTechnicianAttendDate(Timestamp technicianAttendDate) {
        this.technicianAttendDate = technicianAttendDate;
    }

    public Timestamp getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Timestamp completeDate) {
        this.completeDate = completeDate;
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "disasterReportId=" + disasterReportId +
                ", technicianDto=" + (technicianDto==null? "null":technicianDto.toString())+
                ", reportDate=" + reportDate +
                ", delegationDate=" + delegationDate +
                ", technicianAttendDate=" + technicianAttendDate +
                ", completeDate=" + completeDate +
                '}';
    }
}
