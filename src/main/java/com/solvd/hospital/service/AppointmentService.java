package com.solvd.hospital.service;

import com.solvd.hospital.dao.AppointmentDao;
import com.solvd.hospital.dao.DoctorDao;
import com.solvd.hospital.dao.NurseDao;
import com.solvd.hospital.dao.PatientDao;
import com.solvd.hospital.dao.impl.IAppointmentDao;
import com.solvd.hospital.dao.impl.IDoctorDao;
import com.solvd.hospital.dao.impl.INurseDao;
import com.solvd.hospital.dao.impl.IPatientDao;
import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.User;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.service.impl.IAppointmentService;

public class AppointmentService implements IAppointmentService {

    @Override
    public void saveAppointmentToDB(Appointment appointment) {
        IAppointmentDao appointmentDao = new AppointmentDao();
        appointmentDao.insert(appointment);
    }

    @Override
    public void saveDoctorToDB(Doctor doctor) {
        IDoctorDao doctorDao = new DoctorDao();
        doctorDao.insert(doctor);
    }

    @Override
    public void saveNurseToDB(Nurse nurse) {
        INurseDao nurseDao = new NurseDao();
        nurseDao.insert(nurse);
    }

    @Override
    public void savePatientToDB(Patient patient){
        IPatientDao patientDao = new PatientDao();
        patientDao.insert(patient);
    }

    @Override
    public void updatePatientInDB(Patient patient) {
        IPatientDao patientDao = new PatientDao();
        patientDao.update(patient);
    }

    @Override
    public void updateAppointmentInDB(Appointment appointment) {
        IAppointmentDao appointmentDao = new AppointmentDao();
        appointmentDao.update(appointment);
    }

    @Override
    public void updateDoctorInDB(Doctor doctor) {
        IDoctorDao doctorDao = new DoctorDao();
        doctorDao.update(doctor);
    }

    @Override
    public void updateNurseInDB(Nurse nurse) {
        INurseDao nurseDao = new NurseDao();
        nurseDao.update(nurse);
    }

    @Override
    public Patient getPatientInDB(int id) {
        IPatientDao patientDao = new PatientDao();
        return patientDao.getById(id);
    }

    @Override
    public Appointment getAppointmentInDB(int id) {
        IAppointmentDao appointmentDao = new AppointmentDao();
        return appointmentDao.getById(id);
    }

    @Override
    public Doctor getDoctorInDB(int id) {
        IDoctorDao doctorDao = new DoctorDao();
        return doctorDao.getById(id);
    }

    @Override
    public Nurse getNurseInDB(int id) {
        INurseDao nurseDao = new NurseDao();
        return nurseDao.getById(id);
    }

}
