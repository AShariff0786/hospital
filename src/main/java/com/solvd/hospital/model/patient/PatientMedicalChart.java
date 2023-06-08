package com.solvd.hospital.model.patient;

import java.util.Date;

public class PatientMedicalChart {
    private int id;
    private String diagnosis;

    private Date date;
    public PatientMedicalChart(){}
    public PatientMedicalChart (int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
