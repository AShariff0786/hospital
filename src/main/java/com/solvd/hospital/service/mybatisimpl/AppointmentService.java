package com.solvd.hospital.service.mybatisimpl;

import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.service.IAppointmentService;
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

public class AppointmentService implements IAppointmentService {
    private final static Logger LOGGER = LogManager.getLogger(AppointmentService.class);
    private final static String MYBATIS_CONFIG = "mybatis_config.xml";
    private final static String DOC_SAVE = "com.solvd.hospital.db.mapper.DoctorMapper.saveDoctorToDB";
    private final static String DOC_UPDATE = "com.solvd.hospital.db.mapper.DoctorMapper.updateDoctor";
    private final static String DOC_DELETE = "com.solvd.hospital.db.mapper.DoctorMapper.deleteDoctorById";
    private final static String DOC_GET = "com.solvd.hospital.db.mapper.DoctorMapper.selectDoctorById";
    private final static String NUR_SAVE = "com.solvd.hospital.db.mapper.NurseMapper.saveNurseToDB";
    private final static String NUR_UPDATE = "com.solvd.hospital.db.mapper.NurseMapper.updateNurse";
    private final static String NUR_DELETE = "com.solvd.hospital.db.mapper.NurseMapper.deleteNurseById";
    private final static String NUR_GET = "com.solvd.hospital.db.mapper.NurseMapper.selectNurseById";
    private final static String PATIENT_SAVE = "com.solvd.hospital.db.mapper.PatientMapper.savePatientToDB";
    private final static String PATIENT_UPDATE = "com.solvd.hospital.db.mapper.PatientMapper.updatePatient";
    private final static String PATIENT_DELETE = "com.solvd.hospital.db.mapper.PatientMapper.deletePatientById";
    private final static String PATIENT_GET = "com.solvd.hospital.db.mapper.PatientMapper.selectPatientById";
    private final static String APPOINTMENT_SAVE = "com.solvd.hospital.db.mapper.AppointmentMapper.saveAppointmentToDB";
    private final static String APPOINTMENT_UPDATE = "com.solvd.hospital.db.mapper.AppointmentMapper.updateAppointment";
    private final static String APPOINTMENT_DELETE = "com.solvd.hospital.db.mapper.AppointmentMapper.deleteAppointmentById";
    private final static String APPOINTMENT_GET = "com.solvd.hospital.db.mapper.AppointmentMapper.selectAppointmentById";
    @Override
    public void saveAppointmentToDB(Appointment appointment) {
        if(appointment !=null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(APPOINTMENT_SAVE, appointment);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Appointment object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void saveDoctorToDB(Doctor doctor) {
        if(doctor != null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(DOC_SAVE, doctor);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Doctor object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void saveNurseToDB(Nurse nurse) {
        if(nurse != null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(NUR_SAVE, nurse);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Nurse object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void savePatientToDB(Patient patient) {
        if(patient != null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(PATIENT_SAVE, patient);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Patient object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updatePatientInDB(Patient patient) {
        if(patient !=null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(PATIENT_UPDATE, patient);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Patient object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateAppointmentInDB(Appointment appointment) {
        if(appointment != null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(APPOINTMENT_UPDATE, appointment);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Appointment object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateDoctorInDB(Doctor doctor) {
        if(doctor != null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(DOC_UPDATE, doctor);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Doctor object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateNurseInDB(Nurse nurse) {
        if(nurse != null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(NUR_UPDATE, nurse);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Nurse object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public Patient getPatientInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                return session.selectOne(PATIENT_GET, id);
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
    public Appointment getAppointmentInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                return session.selectOne(APPOINTMENT_GET, id);
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
    public Doctor getDoctorInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                return session.selectOne(DOC_GET, id);
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
    public Nurse getNurseInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                return session.selectOne(NUR_GET, id);
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
    public void deleteDoctorInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(DOC_DELETE, id);
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
    public void deletePatientInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(PATIENT_DELETE, id);
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
    public void deleteNurseInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(NUR_DELETE, id);
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
    public void deleteAppointmentInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(APPOINTMENT_DELETE, id);
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
}
