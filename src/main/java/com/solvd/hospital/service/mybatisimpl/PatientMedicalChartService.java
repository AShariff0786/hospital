package com.solvd.hospital.service.mybatisimpl;

import com.solvd.hospital.dao.IPatientMedicalChartDao;
import com.solvd.hospital.model.patient.PatientMedicalChart;
import com.solvd.hospital.service.IPatientMedicalChartService;
import com.solvd.hospital.util.IdException;
import com.solvd.hospital.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PatientMedicalChartService implements IPatientMedicalChartService {
    private final static Logger LOGGER = LogManager.getLogger(PatientMedicalChartService.class);
    @Override
    public void savePatientMedicalChartInDB(PatientMedicalChart pmc) {
        if(pmc != null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IPatientMedicalChartDao patientMedicalChartDao = session.getMapper(IPatientMedicalChartDao.class);
                patientMedicalChartDao.insert(pmc);
                session.commit();
            }
        }else{
            LOGGER.error("PatientMedicalChart object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void deletePatientMedicalChartInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IPatientMedicalChartDao patientMedicalChartDao = session.getMapper(IPatientMedicalChartDao.class);
                patientMedicalChartDao.deleteById(id);
                session.commit();
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public PatientMedicalChart getPatientMedicalChartInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IPatientMedicalChartDao patientMedicalChartDao = session.getMapper(IPatientMedicalChartDao.class);
                return patientMedicalChartDao.getById(id);
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public void updatePatientMedicalChartInDB(PatientMedicalChart pmc) {
        if(pmc != null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IPatientMedicalChartDao patientMedicalChartDao = session.getMapper(IPatientMedicalChartDao.class);
                patientMedicalChartDao.update(pmc);
                session.commit();
            }
        }else{
            LOGGER.error("PatientMedicalChart object is null.");
            throw new NullPointerException();
        }
    }
}
