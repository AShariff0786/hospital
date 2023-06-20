package com.solvd.hospital.service.mybatisimpl;

import com.solvd.hospital.dao.IMedicalBillDao;
import com.solvd.hospital.model.patient.MedicalBill;
import com.solvd.hospital.service.IMedicalBillService;
import com.solvd.hospital.util.IdException;
import com.solvd.hospital.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MedicalBillService implements IMedicalBillService {
    private final static Logger LOGGER = LogManager.getLogger(MedicalBill.class);
    @Override
    public void saveMedicalBillInDB(MedicalBill medicalBill) {
        if(medicalBill != null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IMedicalBillDao medicalBillDao = session.getMapper(IMedicalBillDao.class);
                medicalBillDao.insert(medicalBill);
                session.commit();
            }
        }else{
            LOGGER.error("MedicalBill object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteMedicalBillInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IMedicalBillDao medicalBillDao = session.getMapper(IMedicalBillDao.class);
                medicalBillDao.deleteById(id);
                session.commit();
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public MedicalBill getMedicalBillInDB(int id) {
        if(id>=1) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IMedicalBillDao medicalBillDao = session.getMapper(IMedicalBillDao.class);
                return medicalBillDao.getById(id);
            }
        }else{
            LOGGER.error("Invalid id was entered.");
            throw new IdException("Id must be 1 or greater");
        }
    }

    @Override
    public void updateMedicalBillInDB(MedicalBill medicalBill) {
        if(medicalBill != null) {
            try(SqlSession session = SqlSessionUtil.getSession().openSession();) {
                IMedicalBillDao medicalBillDao = session.getMapper(IMedicalBillDao.class);
                medicalBillDao.update(medicalBill);
                session.commit();
            }
        }else{
            LOGGER.error("Medical Bill object is null.");
            throw new NullPointerException();
        }
    }
}
