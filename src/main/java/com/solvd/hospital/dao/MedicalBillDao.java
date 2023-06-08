package com.solvd.hospital.dao;

import com.solvd.hospital.util.IdException;
import com.solvd.hospital.dao.impl.IMedicalBillDao;
import com.solvd.hospital.model.Insurance;
import com.solvd.hospital.model.patient.MedicalBill;
import com.solvd.hospital.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalBillDao implements IMedicalBillDao {
    private final static Logger LOGGER = LogManager.getLogger(MedicalBillDao.class);
    private static final String INSERT = "INSERT INTO MedicalBill (amountDue, Patient_id, Insurance_id, id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE MedicalBill SET amountDue=? WHERE id=?";
    private static final String DELETE = "DELETE FROM MedicalBill WHERE id=?";
    private static final String GET = "SELECT * FROM MedicalBill WHERE id=?";
    private static final String GET_INSURANCE = "SELECT name FROM Insurance WHERE id=?";
    @Override
    public void insert(MedicalBill medicalBill) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        if(medicalBill == null){
            LOGGER.error("Medical Bill object is null");
            throw new NullPointerException();
        }
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setDouble(1, medicalBill.getAmountDue());
            preparedStatement.setInt(2, medicalBill.getPatient().getId());
            preparedStatement.setInt(3, medicalBill.getInsurance().getId());
            preparedStatement.setInt(4, medicalBill.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error("Unable to close Prepared Statement.");
                throw new RuntimeException(e);
            }
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(MedicalBill medicalBill) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        if(medicalBill == null){
            LOGGER.error("Medical Bill object is null.");
            throw new NullPointerException();
        }
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setDouble(1, medicalBill.getAmountDue());
            preparedStatement.setInt(2, medicalBill.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error("Unable to close Prepared Statement.");
                throw new RuntimeException(e);
            }
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        if(id<=0){
            LOGGER.error("Invalid value for id.");
            throw new IdException("Invalid value for id.");
        }
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error("Unable to close Prepared Statement.");
                throw new RuntimeException(e);
            }
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public MedicalBill getById(int id) {
        MedicalBill medicalBill=new MedicalBill();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if(id<=0){
            LOGGER.error("Invalid value for id.");
            throw new IdException("Invalid value for id.");
        }
        try {
            preparedStatement = connection.prepareStatement(GET);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                medicalBill.setId(resultSet.getInt("id"));
                medicalBill.setAmountDue(resultSet.getDouble("amountDue"));
                medicalBill.setPatient(new PatientDao().getById(resultSet.getInt("Patient_id")));
                medicalBill.setInsurance(new Insurance(resultSet.getInt("Insurance_id")));
            }
            preparedStatement = connection.prepareStatement(GET_INSURANCE);
            preparedStatement.setInt(1, medicalBill.getInsurance().getId());
            while(resultSet.next()){
                medicalBill.getInsurance().setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error("Unable to close resource.");
                throw new RuntimeException(e);
            }
            connectionPool.releaseConnection(connection);
        }
        return medicalBill;
    }
}
