package com.solvd.hospital.service;

import com.solvd.hospital.dao.PatientMedicalChartDao;
import com.solvd.hospital.dao.impl.IPatientMedicalChartDao;
import com.solvd.hospital.model.patient.MedicalBill;
import com.solvd.hospital.model.patient.PatientMedicalChart;
import com.solvd.hospital.service.impl.IPatientMedicalChartService;

public class PatientMedicalChartService implements IPatientMedicalChartService {

    @Override
    public void savePatientMedicalChartInDB(PatientMedicalChart pmc) {
        IPatientMedicalChartDao patientMedicalChartDao = new PatientMedicalChartDao();
        patientMedicalChartDao.insert(pmc);
    }

    @Override
    public void deletePatientMedicalChartInDB(int id) {
        IPatientMedicalChartDao patientMedicalChartDao = new PatientMedicalChartDao();
        patientMedicalChartDao.deleteById(id);
    }

    @Override
    public PatientMedicalChart getPatientMedicalChartInDB(int id) {
        IPatientMedicalChartDao patientMedicalChartDao = new PatientMedicalChartDao();
        PatientMedicalChart pmc = new PatientMedicalChart();
        pmc = patientMedicalChartDao.getById(id);
        return pmc;
    }

    @Override
    public void updatePatientMedicalChartInDB(PatientMedicalChart pmc) {
        IPatientMedicalChartDao patientMedicalChartDao = new PatientMedicalChartDao();
        patientMedicalChartDao.update(pmc);
    }
}
