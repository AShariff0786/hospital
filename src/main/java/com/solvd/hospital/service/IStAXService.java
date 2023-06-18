package com.solvd.hospital.service;

import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;

import java.util.ArrayList;
import java.util.List;

public interface IStAXService {
    ArrayList<Doctor> getDoctorFromXML(String path);

    ArrayList<Nurse> getNurseFromXML(String path);

    ArrayList<Patient> getPatientFromXML(String path);

    void insertDoctorFromXML(Doctor doctor);
    void insertNurseFromXML(Nurse nurse);
    void insertPatientFromXML(Patient patient);
    void validate(String xmlPath, String xsdPath);
    void updatePatientInXML(Patient patient, String path);
    void updateDoctorInXML(Doctor doctor, String path);
    void updateNurseInXML(Nurse nurse, String path);

}
