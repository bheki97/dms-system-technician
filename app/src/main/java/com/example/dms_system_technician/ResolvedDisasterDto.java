package com.example.dms_system_technician;

import com.example.dms_system_technician.enums.DisasterType;

import java.sql.Timestamp;

public class ResolvedDisasterDto {

    private Timestamp reportDate;
    private Timestamp completeDate;
    private DisasterType type;
    private String imgContent;


    public ResolvedDisasterDto() {
    }

    public ResolvedDisasterDto(Timestamp reportDate, Timestamp completeDate, DisasterType type, String imgContent) {
        this.reportDate = reportDate;
        this.completeDate = completeDate;
        this.type = type;
        this.imgContent = imgContent;
    }

    public String getImgContent() {
        return imgContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }

    public Timestamp getReportDate() {
        return reportDate;
    }

    public void setReportDate(Timestamp reportDate) {
        this.reportDate = reportDate;
    }

    public Timestamp getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Timestamp completeDate) {
        this.completeDate = completeDate;
    }

    public DisasterType getType() {
        return type;
    }

    public void setType(DisasterType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ResolvedDisasterDto{" +
                "reportDate=" + reportDate +
                ", completeDate=" + completeDate +
                ", type=" + type +
                '}';
    }
}
