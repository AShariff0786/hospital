package com.solvd.hospital.model.patient;

import com.solvd.hospital.model.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement (name = "patient")
public class Patient extends User {
    @XmlElement (name = "address")
    private String address;
    @XmlElement (name = "phoneNumber")
    private String phoneNumber;
    @XmlElement (name = "doctor")
    private Doctor doctor;
    @XmlElement (name = "nurse")
    private Nurse nurse;
    @XmlElement (name = "insurance")
    private Insurance insurance;
    @XmlElement (name = "chart")
    private PatientMedicalChart chart;
    @XmlElementWrapper(name = "appointments")
    @XmlElement (name = "appointment")
    private List<Appointment> appointments;

    public Patient(){}

    public Patient(int id){
        super(id);
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
