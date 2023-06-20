package com.solvd.hospital.service.mybatisimpl;

import com.solvd.hospital.dao.IAppointmentDao;
import com.solvd.hospital.dao.IDoctorDao;
import com.solvd.hospital.dao.INurseDao;
import com.solvd.hospital.dao.IPatientDao;
import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.service.IAppointmentService;
import com.solvd.hospital.util.IdException;
import com.solvd.hospital.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AppointmentService implements IAppointmentService {
    private final static Logger LOGGER = LogManager.getLogger(AppointmentService.class);

    @Override
    public void saveAppointmentToDB(Appointment appointment) {
        if(appointment !=null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IAppointmentDao appointmentDao = session.getMapper(IAppointmentDao.class);
                appointmentDao.insert(appointment);
                session.commit();
            }
        }else{
            LOGGER.error("Appointment object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void saveDoctorToDB(Doctor doctor) {
        if(doctor != null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IDoctorDao doctorDao = session.getMapper(IDoctorDao.class);
                doctorDao.insert(doctor);
                session.commit();
            }
        }else{
            LOGGER.error("Doctor object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void saveNurseToDB(Nurse nurse) {
        if(nurse != null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                INurseDao nurseDao = session.getMapper(INurseDao.class);
                nurseDao.insert(nurse);
                session.commit();
            }
        }else{
            LOGGER.error("Nurse object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void savePatientToDB(Patient patient) {
        if(patient != null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IPatientDao patientDao = session.getMapper(IPatientDao.class);
                patientDao.insert(patient);
                session.commit();
            }
        }else{
            LOGGER.error("Patient object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updatePatientInDB(Patient patient) {
        if(patient !=null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IPatientDao patientDao = session.getMapper(IPatientDao.class);
                patientDao.update(patient);
                session.commit();
            }
        }else{
            LOGGER.error("Patient object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateAppointmentInDB(Appointment appointment) {
        if(appointment != null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IAppointmentDao appointmentDao = session.getMapper(IAppointmentDao.class);
                appointmentDao.update(appointment);
                session.commit();
            }
        }else{
            LOGGER.error("Appointment object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateDoctorInDB(Doctor doctor) {
        if(doctor != null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IDoctorDao doctorDao = session.getMapper(IDoctorDao.class);
                doctorDao.update(doctor);
                session.commit();
            }
        }else{
            LOGGER.error("Doctor object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateNurseInDB(Nurse nurse) {
        if(nurse != null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                INurseDao nurseDao = session.getMapper(INurseDao.class);
                nurseDao.update(nurse);
                session.commit();
            }
        }else{
            LOGGER.error("Nurse object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public Patient getPatientInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IPatientDao patientDao = session.getMapper(IPatientDao.class);
                return patientDao.getById(id);
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public Appointment getAppointmentInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IAppointmentDao appointmentDao = session.getMapper(IAppointmentDao.class);
                return appointmentDao.getById(id);
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public Doctor getDoctorInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IDoctorDao doctorDao = session.getMapper(IDoctorDao.class);
                return doctorDao.getById(id);
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public Nurse getNurseInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                INurseDao nurseDao = session.getMapper(INurseDao.class);
                return nurseDao.getById(id);
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public void deleteDoctorInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IDoctorDao doctorDao = session.getMapper(IDoctorDao.class);
                doctorDao.deleteById(id);
                session.commit();
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public void deletePatientInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IPatientDao patientDao = session.getMapper(IPatientDao.class);
                patientDao.deleteById(id);
                session.commit();
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public void deleteNurseInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                INurseDao nurseDao = session.getMapper(INurseDao.class);
                nurseDao.deleteById(id);
                session.commit();
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public void deleteAppointmentInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IAppointmentDao appointmentDao = session.getMapper(IAppointmentDao.class);
                appointmentDao.deleteById(id);
                session.commit();
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }
}
