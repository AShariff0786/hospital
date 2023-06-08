package com.solvd.hospital.model.patient;

import com.solvd.hospital.model.Symptom;

import java.util.Date;

public class SymptomInformation {
    private PatientMedicalChart patientMedicalChart;
    private Symptom symptom;
    private Date start;
    private Date end;

    public SymptomInformation(){}
    public SymptomInformation(PatientMedicalChart patientMedicalChart, Symptom symptom){
        this.patientMedicalChart = patientMedicalChart;
        this.symptom = symptom;
    }

    public PatientMedicalChart getPatientMedicalChart() {
        return patientMedicalChart;
    }

    public void setPatientMedicalChart(PatientMedicalChart patientMedicalChart) {
        this.patientMedicalChart = patientMedicalChart;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
