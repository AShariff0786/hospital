package com.solvd.hospital.service.impl;

import com.solvd.hospital.dao.*;
import com.solvd.hospital.dao.impl.*;
import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.model.patient.PatientMedicalChart;
import com.solvd.hospital.service.IAppointmentService;
import com.solvd.hospital.util.IdException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppointmentService implements IAppointmentService {
    private final static Logger LOGGER = LogManager.getLogger(AppointmentService.class);

    @Override
    public void saveAppointmentToDB(Appointment appointment) {
        if(appointment != null ) {
            IAppointmentDao appointmentDao = new AppointmentDao();
            appointmentDao.insert(appointment);
        }else{
            LOGGER.error("Appointment object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void saveDoctorToDB(Doctor doctor) {
        if(doctor != null) {
            IDoctorDao doctorDao = new DoctorDao();
            doctorDao.insert(doctor);
        }else{
            LOGGER.error("Doctor object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void saveNurseToDB(Nurse nurse) {
        if(nurse != null) {
            INurseDao nurseDao = new NurseDao();
            nurseDao.insert(nurse);
        }else {
            LOGGER.error("Nurse object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void savePatientToDB(Patient patient){
        if(patient != null) {
            IPatientDao patientDao = new PatientDao();
            patientDao.insert(patient);
        }else{
            LOGGER.error("Patient object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updatePatientInDB(Patient patient) {
        if(patient != null) {
            IPatientDao patientDao = new PatientDao();
            patientDao.update(patient);
        }else{
            LOGGER.error("Patient object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateAppointmentInDB(Appointment appointment) {
        if(appointment != null) {
            IAppointmentDao appointmentDao = new AppointmentDao();
            appointmentDao.update(appointment);
        }else{
            LOGGER.error("Appointment object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateDoctorInDB(Doctor doctor) {
        if(doctor != null) {
            IDoctorDao doctorDao = new DoctorDao();
            doctorDao.update(doctor);
        }else{
            LOGGER.error("Doctor object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateNurseInDB(Nurse nurse) {
        if(nurse != null) {
            INurseDao nurseDao = new NurseDao();
            nurseDao.update(nurse);
        }else{
            LOGGER.error("Nurse object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public Patient getPatientInDB(int id) {
        if(id>=1) {
            IPatientDao patientDao = new PatientDao();
            Patient patient = patientDao.getById(id);
            IDoctorDao doctorDao = new DoctorDao();
            Doctor doctor = doctorDao.getById(patient.getDoctor().getId());
            patient.setDoctor(doctor);
            INurseDao nurseDao = new NurseDao();
            Nurse nurse = nurseDao.getById(patient.getNurse().getId());
            patient.setNurse(nurse);
            IPatientMedicalChartDao patientMedicalChartDao = new PatientMedicalChartDao();
            PatientMedicalChart pmc = patientMedicalChartDao.getById(patient.getChart().getId());
            patient.setChart(pmc);
            return patient;
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public Appointment getAppointmentInDB(int id) {
        if(id>=1) {
            IAppointmentDao appointmentDao = new AppointmentDao();
            Appointment appointment = appointmentDao.getById(id);
            IDoctorDao doctorDao = new DoctorDao();
            Doctor doctor = doctorDao.getById(appointment.getDoctor().getId());
            appointment.setDoctor(doctor);
            INurseDao nurseDao = new NurseDao();
            Nurse nurse = nurseDao.getById(appointment.getNurse().getId());
            appointment.setNurse(nurse);
            IPatientDao patientDao = new PatientDao();
            Patient patient = patientDao.getById(appointment.getPatient().getId());
            appointment.setPatient(patient);
            return appointment;
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public Doctor getDoctorInDB(int id) {
        if(id>=1) {
            IDoctorDao doctorDao = new DoctorDao();
            return doctorDao.getById(id);
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public Nurse getNurseInDB(int id) {
        if(id>=1) {
            INurseDao nurseDao = new NurseDao();
            return nurseDao.getById(id);
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

}
