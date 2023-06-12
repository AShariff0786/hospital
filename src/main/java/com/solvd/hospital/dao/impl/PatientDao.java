package com.solvd.hospital.dao.impl;

import com.solvd.hospital.dao.IPatientDao;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Insurance;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.model.patient.PatientMedicalChart;
import com.solvd.hospital.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDao implements IPatientDao {
    private final static Logger LOGGER = LogManager.getLogger(PatientDao.class);
    private static final String INSERT = "INSERT INTO Patient (name, Doctor_employeeId, PatientMedicalChart_reportId, " +
            "Nurse_employeeId, Insurance_id, id, address, phoneNumber) VALUES (?, ?, ?, ?,?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Patient SET name=?, address=?, phoneNumber=? WHERE id=?";
    private static final String DELETE = "DELETE FROM Patient WHERE id=?";
    private static final String GET = "SELECT * FROM Patient WHERE id=?";
    @Override
    public void insert(Patient patient) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT);) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setInt(2, patient.getDoctor().getId());
            preparedStatement.setInt(3, patient.getChart().getId());
            preparedStatement.setInt(4, patient.getNurse().getId());
            preparedStatement.setInt(5, patient.getInsurance().getId());
            preparedStatement.setInt(6, patient.getId());
            preparedStatement.setString(7, patient.getAddress());
            preparedStatement.setString(8, patient.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Patient patient) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);){
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getAddress());
            preparedStatement.setString(3, patient.getPhoneNumber());
            preparedStatement.setInt(4, patient.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE);){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Patient getById(int id) {
        Patient patient = new Patient();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET);){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    patient.setId(resultSet.getInt("id"));
                    patient.setName(resultSet.getString("name"));
                    patient.setAddress(resultSet.getString("address"));
                    patient.setPhoneNumber(resultSet.getString("phoneNumber"));
                    patient.setDoctor(new Doctor(resultSet.getInt("Doctor_employeeId")));
                    patient.setNurse(new Nurse(resultSet.getInt("Nurse_employeeId")));
                    patient.setInsurance(new Insurance(resultSet.getInt("Insurance_id")));
                    patient.setChart(new PatientMedicalChart(resultSet.getInt("PatientMedicalChart_reportId")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return patient;
    }

}
