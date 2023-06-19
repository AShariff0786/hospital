package com.solvd.hospital.service.mybatisimpl;

import com.solvd.hospital.model.patient.MedicalBill;
import com.solvd.hospital.service.IMedicalBillService;
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

public class MedicalBillService implements IMedicalBillService {
    private final static Logger LOGGER = LogManager.getLogger(MedicalBill.class);
    private final static String MYBATIS_CONFIG = "mybatis_config.xml";
    private final static String MEDICAL_BILL_SAVE = "com.solvd.hospital.db.mapper.MedicalBillMapper.saveMedicalBillToDB";
    private final static String MEDICAL_BILL_UPDATE = "com.solvd.hospital.db.mapper.MedicalBillMapper.updateMedicalBill";
    private final static String MEDICAL_BILL_DELETE = "com.solvd.hospital.db.mapper.MedicalBillMapper.deleteMedicalBillById";
    private final static String MEDICAL_BILL_GET = "com.solvd.hospital.db.mapper.MedicalBillMapper.selectMedicalBillById";
    @Override
    public void saveMedicalBillInDB(MedicalBill medicalBill) {
        if(medicalBill != null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(MEDICAL_BILL_SAVE, medicalBill);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("MedicalBill object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteMedicalBillInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(MEDICAL_BILL_DELETE, id);
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
    public MedicalBill getMedicalBillInDB(int id) {
        if(id>=1) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                return session.selectOne(MEDICAL_BILL_GET, id);
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
    public void updateMedicalBillInDB(MedicalBill medicalBill) {
        if(medicalBill != null) {
            Properties prop = PropertiesUtil.getProperties();
            try (InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {
                session.selectOne(MEDICAL_BILL_UPDATE, medicalBill);
                session.commit();
            } catch (IOException e) {
                LOGGER.error("File Not Found");
                throw new RuntimeException(e);
            }
        }else{
            LOGGER.error("Medical Bill object is null.");
            throw new NullPointerException();
        }
    }
}
