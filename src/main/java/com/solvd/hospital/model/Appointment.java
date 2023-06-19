package com.solvd.hospital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.util.CustomDateAdapter;
import com.solvd.hospital.util.CustomTimeAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;
import java.util.Date;

@JsonRootName(value = "appointment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "appointment")
public class Appointment {
    @JsonProperty(value = "id")
    @XmlAttribute
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
    @XmlJavaTypeAdapter(CustomDateAdapter.class)
    private Date date;
    @JsonFormat(pattern="HH:mm")
    @XmlJavaTypeAdapter(CustomTimeAdapter.class)
    private LocalTime time;
    @JsonIgnore
    @XmlTransient
    private Doctor doctor;
    @JsonIgnore
    @XmlTransient
    private Nurse nurse;
    @JsonIgnore
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
