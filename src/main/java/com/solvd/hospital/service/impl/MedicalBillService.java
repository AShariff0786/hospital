package com.solvd.hospital.service.impl;

import com.solvd.hospital.dao.impl.MedicalBillDao;
import com.solvd.hospital.dao.IMedicalBillDao;
import com.solvd.hospital.model.patient.MedicalBill;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.service.IAppointmentService;
import com.solvd.hospital.service.IMedicalBillService;
import com.solvd.hospital.util.IdException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedicalBillService implements IMedicalBillService {
    private final static Logger LOGGER = LogManager.getLogger(MedicalBillService.class);

    @Override
    public void saveMedicalBillInDB(MedicalBill medicalBill) {
        if(medicalBill != null) {
            IMedicalBillDao medicalBillDao = new MedicalBillDao();
            medicalBillDao.insert(medicalBill);
        }else{
            LOGGER.error("MedicalBill object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteMedicalBillInDB(int id) {
        if(id>=1) {
            IMedicalBillDao medicalBillDao = new MedicalBillDao();
            medicalBillDao.deleteById(id);
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public MedicalBill getMedicalBillInDB(int id) {
        if(id>=1) {
            IMedicalBillDao medicalBillDao = new MedicalBillDao();
            MedicalBill medicalBill = medicalBillDao.getById(id);
            IAppointmentService appointmentService = new AppointmentService();
            Patient patient = appointmentService.getPatientInDB(medicalBill.getPatient().getId());
            medicalBill.setPatient(patient);
            return medicalBill;
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public void updateMedicalBillInDB(MedicalBill medicalBill) {
        if(medicalBill != null) {
            IMedicalBillDao medicalBillDao = new MedicalBillDao();
            medicalBillDao.update(medicalBill);
        }else{
            LOGGER.error("MedicalBill object is null.");
            throw new NullPointerException();
        }
    }
}
