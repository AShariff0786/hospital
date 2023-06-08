package com.solvd.hospital.model.patient;

import com.solvd.hospital.model.*;

import java.util.List;

public class Patient extends User {
    private String address;
    private String phoneNumber;
    private Doctor doctor;
    private Nurse nurse;
    private Insurance insurance;
    private PatientMedicalChart chart;
    private List<Appointment> appointments;

    public Patient(){}

    public Patient(int id, String name){
        super(id, name);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public PatientMedicalChart getChart() {
        return chart;
    }

    public void setChart(PatientMedicalChart chart) {
        this.chart = chart;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
