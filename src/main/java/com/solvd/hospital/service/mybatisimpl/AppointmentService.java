package com.solvd.hospital.service.mybatisimpl;

import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.service.IAppointmentService;
import com.solvd.hospital.util.PropertiesUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppointmentService implements IAppointmentService {
    private final static Logger LOGGER = LogManager.getLogger(AppointmentService.class);
    private final static String mybatisConfig = "mybatis_config.xml";
    private final static String docSave = "com.solvd.hospital.db.mapper.DoctorMapper.saveDoctorToDB";
    private final static String docUpdate = "com.solvd.hospital.db.mapper.DoctorMapper.updateDoctor";
    private final static String docDelete = "com.solvd.hospital.db.mapper.DoctorMapper.deleteDoctorById";
    private final static String docGet = "com.solvd.hospital.db.mapper.DoctorMapper.selectDoctorById";
    private final static String nurSave = "com.solvd.hospital.db.mapper.NurseMapper.saveNurseToDB";
    private final static String nurUpdate = "com.solvd.hospital.db.mapper.NurseMapper.updateNurse";
    private final static String nurDelete = "com.solvd.hospital.db.mapper.NurseMapper.deleteNurseById";
    private final static String nurGet = "com.solvd.hospital.db.mapper.NurseMapper.selectNurseById";
    private final static String patientSave = "com.solvd.hospital.db.mapper.PatientMapper.savePatientToDB";
    private final static String patientUpdate = "com.solvd.hospital.db.mapper.PatientMapper.updatePatient";
    private final static String patientDelete = "com.solvd.hospital.db.mapper.PatientMapper.deletePatientById";
    private final static String patientGet = "com.solvd.hospital.db.mapper.PatientMapper.selectPatientById";
    @Override
    public void saveAppointmentToDB(Appointment appointment) {

    }

    @Override
    public void saveDoctorToDB(Doctor doctor) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            session.selectOne(docSave, doctor);
            session.commit();
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveNurseToDB(Nurse nurse) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            session.selectOne(nurSave, nurse);
            session.commit();
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void savePatientToDB(Patient patient) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            session.selectOne(patientSave, patient);
            session.commit();
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePatientInDB(Patient patient) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            session.selectOne(patientUpdate, patient);
            session.commit();
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAppointmentInDB(Appointment appointment) {

    }

    @Override
    public void updateDoctorInDB(Doctor doctor) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            session.selectOne(docUpdate, doctor);
            session.commit();
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateNurseInDB(Nurse nurse) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            session.selectOne(nurUpdate, nurse);
            session.commit();
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Patient getPatientInDB(int id) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            return session.selectOne(patientGet, id);
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Appointment getAppointmentInDB(int id) {
        return null;
    }

    @Override
    public Doctor getDoctorInDB(int id) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            return session.selectOne(docGet, id);
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Nurse getNurseInDB(int id) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            return session.selectOne(nurGet, id);
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDoctorInDB(int id) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            session.selectOne(docDelete, id);
            session.commit();
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePatientInDB(int id) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            session.selectOne(patientDelete, id);
            session.commit();
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteNurseInDB(int id) {
        Properties prop = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(mybatisConfig);
            SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
            session.selectOne(nurDelete, id);
            session.commit();
        } catch (IOException e) {
            LOGGER.error("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAppointmentInDB(int id) {

    }
}
