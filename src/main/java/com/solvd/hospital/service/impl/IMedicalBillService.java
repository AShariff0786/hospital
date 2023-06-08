package com.solvd.hospital.service.impl;


import com.solvd.hospital.model.patient.MedicalBill;

public interface IMedicalBillService {
    void saveMedicalBillInDB(MedicalBill medicalBill);
    void deleteMedicalBillInDB(int id);
    MedicalBill getMedicalBillInDB(int id);
    void updateMedicalBillInDB(MedicalBill medicalBill);
}
