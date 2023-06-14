package com.solvd.hospital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.hospital.model.patient.Patient;

import javax.xml.bind.annotation.*;
import java.util.List;

@JsonRootName(value = "nurse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name= "nurse")
public class Nurse extends User{
    @JsonProperty(value = "position")
    @XmlElement(name = "position")
    private String position;
    @JsonProperty(value = "department")
    @XmlElement (name = "department")
    private Department department;
    @JsonIgnore
    @XmlTransient
    private List<Patient> patients;
    @JsonIgnore
    @XmlTransient
    private List<Appointment> appointments;

    public Nurse(){}
    public Nurse(int id){
        super(id);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
