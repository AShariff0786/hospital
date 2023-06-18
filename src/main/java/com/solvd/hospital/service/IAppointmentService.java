package com.solvd.hospital.service;

import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;

public interface IAppointmentService {
    void saveAppointmentToDB(Appointment appointment);
    void saveDoctorToDB(Doctor doctor);
    void saveNurseToDB(Nurse nurse);
    void savePatientToDB(Patient patient);
    void updatePatientInDB(Patient patient);
    void updateAppointmentInDB(Appointment appointment);
    void updateDoctorInDB(Doctor doctor);
    void updateNurseInDB(Nurse nurse);

    Patient getPatientInDB(int id);
    Appointment getAppointmentInDB(int id);
    Doctor getDoctorInDB(int id);
    Nurse getNurseInDB(int id);

    void deleteDoctorInDB(int id);
    void deletePatientInDB(int id);
    void deleteNurseInDB(int id);
    void deleteAppointmentInDB(int id);

}
