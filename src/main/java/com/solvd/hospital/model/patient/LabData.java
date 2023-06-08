package com.solvd.hospital.model.patient;

import com.solvd.hospital.model.Lab;

import java.util.Date;

public class LabData {
    private MedicalBill medicalBill;
    private Lab lab;
    private String testResults;
    private Date dateOfTest;

    public LabData(){}
    public LabData(MedicalBill medicalBill, Lab lab){
        this.medicalBill = medicalBill;
        this.lab = lab;
    }

    public MedicalBill getMedicalBill() {
        return medicalBill;
    }

    public void setMedicalBill(MedicalBill medicalBill) {
        this.medicalBill = medicalBill;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public Date getDateOfTest() {
        return dateOfTest;
    }

    public void setDateOfTest(Date dateOfTest) {
        this.dateOfTest = dateOfTest;
    }
}
