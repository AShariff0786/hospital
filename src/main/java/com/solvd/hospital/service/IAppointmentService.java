package com.solvd.hospital.service;

import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.model.patient.TreatmentData;

import java.io.File;

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

    void serializeNurse(Nurse nurse, File file);

    Nurse deserializeNurse(File file);

    void serializeDoctor(Doctor doctor, File file);

    Doctor deserializeDoctor(File file);
    void serializePatient(Patient patient, File file);

    Patient deserializePatient(File file);


}
