package com.solvd.hospital.service;

import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.service.AppointmentService;
import com.solvd.hospital.service.impl.IStAXService;

public class StAXService implements IStAXService {
    @Override
    public void insertDoctorFromXML(Doctor doctor){
        AppointmentService appointmentService = new AppointmentService();
        appointmentService.saveDoctorToDB(doctor);
    }

    @Override
    public void insertNurseFromXML(Nurse nurse){
        AppointmentService appointmentService = new AppointmentService();
        appointmentService.saveNurseToDB(nurse);
    }

    @Override
    public void insertPatientFromXML(Patient patient){
        AppointmentService appointmentService = new AppointmentService();
        appointmentService.savePatientToDB(patient);
    }

}
