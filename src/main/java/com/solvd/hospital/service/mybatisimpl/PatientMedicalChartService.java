package com.solvd.hospital.service.mybatisimpl;

import com.solvd.hospital.model.patient.PatientMedicalChart;
import com.solvd.hospital.service.IPatientMedicalChartService;
import com.solvd.hospital.util.IdException;
import com.solvd.hospital.util.PropertiesUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PatientMedicalChartService implements IPatientMedicalChartService {
    private final static Logger LOGGER = LogManager.getLogger(PatientMedicalChart.class);
    private final static String MYBATIS_CONFIG = "mybatis_config.xml";
    private final static String PMC_SAVE = "com.solvd.hospital.db.mappers.PatientMedicalChartMapper.savePatientMedicalChartToDB";
    private final static String PMC_UPDATE = "com.solvd.hospital.db.mappers.PatientMedicalChartMapper.updatePatientMedicalChart";
    private final static String PMC_DELETE = "com.solvd.hospital.db.mappers.PatientMedicalChartMapper.deletePatientMedicalChartById";
    private final static String PMC_GET = "com.solvd.hospital.db.mappers.PatientMedicalChartMapper.selectPatientMedicalChartById";
    @Override
    public void savePatientMedicalChartInDB(PatientMedicalChart pmc) {
        if(pmc != null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(PMC_SAVE, pmc);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("PatientMedicalChart object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void deletePatientMedicalChartInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(PMC_DELETE, id);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public PatientMedicalChart getPatientMedicalChartInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                return session.selectOne(PMC_GET, id);
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public void updatePatientMedicalChartInDB(PatientMedicalChart pmc) {
        if(pmc != null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(PMC_UPDATE, pmc);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("PatientMedicalChart object is null.");
            throw new NullPointerException();
        }
    }
}
