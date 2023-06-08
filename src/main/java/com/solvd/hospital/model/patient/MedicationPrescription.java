package com.solvd.hospital.model.patient;

import com.solvd.hospital.model.Medication;

public class MedicationPrescription {
    private Medication medication;
    private PatientMedicalChart patientMedicalChart;
    private int dosage;
    private int numRefills;

    public MedicationPrescription(){}
    public MedicationPrescription(Medication medication, PatientMedicalChart patientMedicalChart){
        this.medication = medication;
        this.patientMedicalChart = patientMedicalChart;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public PatientMedicalChart getPatientMedicalChart() {
        return patientMedicalChart;
    }

    public void setPatientMedicalChart(PatientMedicalChart patientMedicalChart) {
        this.patientMedicalChart = patientMedicalChart;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getNumRefills() {
        return numRefills;
    }

    public void setNumRefills(int numRefills) {
        this.numRefills = numRefills;
    }
}
