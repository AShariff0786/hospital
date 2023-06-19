package com.solvd.hospital.model.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Insurance;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.User;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@JsonRootName(value = "patient")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement (name = "patient")
public class Patient extends User {
    @JsonProperty(value = "address")
    @XmlElement (name = "address")
    private String address;
    @JsonProperty(value = "phoneNumber")
    @XmlElement (name = "phoneNumber")
    private String phoneNumber;
    @JsonProperty(value = "doctor")
    @XmlElement (name = "doctor")
    private Doctor doctor;
    @JsonProperty(value = "nurse")
    @XmlElement (name = "nurse")
    private Nurse nurse;
    @JsonProperty(value = "insurance")
    @XmlElement (name = "insurance")
    private Insurance insurance;
    @JsonProperty(value = "chart")
    @XmlElement (name = "chart")
    private PatientMedicalChart chart;
    @JsonProperty(value = "appointments")
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
