package com.solvd.hospital.service.impl;

import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;

public interface IStAXService {
    void insertDoctorFromXML(Doctor doctor);
    void insertNurseFromXML(Nurse nurse);
    void insertPatientFromXML(Patient patient);

}
