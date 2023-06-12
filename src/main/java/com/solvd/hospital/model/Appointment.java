package com.solvd.hospital.model;

import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.util.CustomDateAdapter;
import com.solvd.hospital.util.CustomTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;
import java.util.Date;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement (name = "appointment")
public class Appointment {
    @XmlAttribute
    private int id;
    @XmlJavaTypeAdapter(CustomDateAdapter.class)
    private Date date;
    @XmlJavaTypeAdapter(CustomTimeAdapter.class)
    private LocalTime time;
    @XmlTransient
    private Doctor doctor;
    @XmlTransient
    private Nurse nurse;
    @XmlTransient
    private Patient patient;

    public Appointment(){}
    public Appointment(int id, Date date, LocalTime time){
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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
