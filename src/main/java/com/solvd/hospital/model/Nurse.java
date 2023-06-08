package com.solvd.hospital.model;

import com.solvd.hospital.model.patient.Patient;

import java.util.List;

public class Nurse extends User{
    private String position;
    private Department department;
    private List<Patient> patients;
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
