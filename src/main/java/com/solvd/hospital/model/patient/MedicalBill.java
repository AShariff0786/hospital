package com.solvd.hospital.model.patient;

import com.solvd.hospital.model.Insurance;

public class MedicalBill {
    private int id;
    private double amountDue;
    private Patient patient;
    private Insurance insurance;

    public MedicalBill(){}
    public MedicalBill(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
