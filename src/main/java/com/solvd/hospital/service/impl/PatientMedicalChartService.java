package com.solvd.hospital.service.impl;

import com.solvd.hospital.dao.impl.PatientMedicalChartDao;
import com.solvd.hospital.dao.IPatientMedicalChartDao;
import com.solvd.hospital.model.patient.PatientMedicalChart;
import com.solvd.hospital.service.IPatientMedicalChartService;
import com.solvd.hospital.util.IdException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatientMedicalChartService implements IPatientMedicalChartService {
    private final static Logger LOGGER = LogManager.getLogger(PatientMedicalChart.class);

    @Override
    public void savePatientMedicalChartInDB(PatientMedicalChart pmc) {
        if(pmc != null ) {
            IPatientMedicalChartDao patientMedicalChartDao = new PatientMedicalChartDao();
            patientMedicalChartDao.insert(pmc);
        }else{
            LOGGER.error("PatientMedicalChart object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void deletePatientMedicalChartInDB(int id) {
        if(id>=1) {
            IPatientMedicalChartDao patientMedicalChartDao = new PatientMedicalChartDao();
            patientMedicalChartDao.deleteById(id);
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public PatientMedicalChart getPatientMedicalChartInDB(int id) {
        if(id>=1) {
            IPatientMedicalChartDao patientMedicalChartDao = new PatientMedicalChartDao();
            return patientMedicalChartDao.getById(id);
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public void updatePatientMedicalChartInDB(PatientMedicalChart pmc) {
        if(pmc != null) {
            IPatientMedicalChartDao patientMedicalChartDao = new PatientMedicalChartDao();
            patientMedicalChartDao.update(pmc);
        }else{
            LOGGER.error("PatientMedicalChart object is null.");
            throw new NullPointerException();
        }
    }
}
