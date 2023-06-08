package com.solvd.hospital.service.impl;

import com.solvd.hospital.model.patient.PatientMedicalChart;

public interface IPatientMedicalChartService {
    void savePatientMedicalChartInDB(PatientMedicalChart pmc);
    void deletePatientMedicalChartInDB(int id);
    PatientMedicalChart getPatientMedicalChartInDB(int id);
    void updatePatientMedicalChartInDB(PatientMedicalChart pmc);
}
