package com.solvd.hospital.service;

import com.solvd.hospital.dao.MedicalBillDao;
import com.solvd.hospital.dao.impl.IMedicalBillDao;
import com.solvd.hospital.model.patient.MedicalBill;
import com.solvd.hospital.service.impl.IMedicalBillService;

public class MedicalBillService implements IMedicalBillService {

    @Override
    public void saveMedicalBillInDB(MedicalBill medicalBill) {
        IMedicalBillDao medicalBillDao = new MedicalBillDao();
        medicalBillDao.insert(medicalBill);
    }

    @Override
    public void deleteMedicalBillInDB(int id) {
        IMedicalBillDao medicalBillDao = new MedicalBillDao();
        medicalBillDao.deleteById(id);
    }

    @Override
    public MedicalBill getMedicalBillInDB(int id) {
        IMedicalBillDao medicalBillDao = new MedicalBillDao();
        MedicalBill medicalBill = medicalBillDao.getById(id);
        return medicalBill;
    }

    @Override
    public void updateMedicalBillInDB(MedicalBill medicalBill) {
        IMedicalBillDao medicalBillDao = new MedicalBillDao();
        medicalBillDao.update(medicalBill);
    }
}
