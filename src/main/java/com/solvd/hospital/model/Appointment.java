package com.solvd.hospital.model;

import com.solvd.hospital.model.patient.Patient;

import java.sql.Time;
import java.util.Date;

public class Appointment {
    private int id;
    private Date date;
    private Time time;
    private Doctor doctor;
    private Nurse nurse;
    private Patient patient;

    public Appointment(){}
    public Appointment(int id, Date date, Time time){
        this.id= id;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
