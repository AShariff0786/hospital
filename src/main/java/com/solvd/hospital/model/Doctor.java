package com.solvd.hospital.model;

import com.solvd.hospital.model.patient.Patient;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name ="doctor")
public class Doctor extends User{
    @XmlElement(name = "position")
    private String position;
    @XmlElement (name = "department")
    private Department department;
    @XmlTransient
    private List<Patient> patients;
    @XmlTransient
    private List<Appointment> appointments;

    public Doctor(){}
    public Doctor(int id){
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
