package com.solvd.hospital.model.patient;

import com.solvd.hospital.model.Treatment;

import java.util.Date;

public class TreatmentData {
    private MedicalBill medicalBill;
    private Treatment treatment;
    private Date startOfTreatment;
    private Date endOfTreatment;

    public TreatmentData(){}
    public TreatmentData(MedicalBill medicalBill, Treatment treatment){
        this.medicalBill = medicalBill;
        this.treatment = treatment;
    }

    public MedicalBill getMedicalBill() {
        return medicalBill;
    }

    public void setMedicalBill(MedicalBill medicalBill) {
        this.medicalBill = medicalBill;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Date getStartOfTreatment() {
        return startOfTreatment;
    }

    public void setStartOfTreatment(Date startOfTreatment) {
        this.startOfTreatment = startOfTreatment;
    }

    public Date getEndOfTreatment() {
        return endOfTreatment;
    }

    public void setEndOfTreatment(Date endOfTreatment) {
        this.endOfTreatment = endOfTreatment;
    }
}
