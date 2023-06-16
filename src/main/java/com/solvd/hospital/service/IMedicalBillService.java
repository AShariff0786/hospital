package com.solvd.hospital.service;


import com.solvd.hospital.model.Insurance;
import com.solvd.hospital.model.patient.MedicalBill;
import com.solvd.hospital.model.patient.TreatmentData;

import java.io.File;

public interface IMedicalBillService {
    void saveMedicalBillInDB(MedicalBill medicalBill);
    void deleteMedicalBillInDB(int id);
    MedicalBill getMedicalBillInDB(int id);
    void updateMedicalBillInDB(MedicalBill medicalBill);

    void serializeInsurance(Insurance insurance, String file);

    Insurance deserializeInsurance(String file);
}
