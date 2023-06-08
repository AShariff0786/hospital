package com.solvd.hospital.dao;

import com.solvd.hospital.util.IdException;
import com.solvd.hospital.dao.impl.IPatientMedicalChartDao;
import com.solvd.hospital.model.patient.PatientMedicalChart;
import com.solvd.hospital.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientMedicalChartDao implements IPatientMedicalChartDao {
    private final static Logger LOGGER = LogManager.getLogger(PatientMedicalChart.class);
    private final static String INSERT = "INSERT INTO PatientMedicalChart (diagnosis, dateVisted, reportId) VALUES ( ?,?, ?)";
    private final static String UPDATE = "UPDATE PatientMedicalChart SET diagnosis=?, dateVisted=? WHERE reportId=?";
    private final static String DELETE = "DELETE FROM PatientMedicalChart WHERE reportId=?";
    private final static String GET = "SELECT * FROM PatientMedicalChart WHERE id=?";
    @Override
    public void insert(PatientMedicalChart patientMedicalChart) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        if(patientMedicalChart == null){
            LOGGER.error("PatientMedicalChart object is null.");
            throw new NullPointerException();
        }
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, patientMedicalChart.getDiagnosis());
            preparedStatement.setDate(2, (java.sql.Date) patientMedicalChart.getDate());
            preparedStatement.setInt(3, patientMedicalChart.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
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
    public void update(PatientMedicalChart patientMedicalChart) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        if(patientMedicalChart == null){
            LOGGER.error("PatientMedicalChart object is null.");
            throw new NullPointerException();
        }
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, patientMedicalChart.getDiagnosis());
            preparedStatement.setDate(2, (java.sql.Date) patientMedicalChart.getDate());
            preparedStatement.setInt(3, patientMedicalChart.getId());
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
    public PatientMedicalChart getById(int id) {
        PatientMedicalChart patientMedicalChart=new PatientMedicalChart();
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
                patientMedicalChart.setId(resultSet.getInt("reportId"));
                patientMedicalChart.setDiagnosis(resultSet.getString("diagnosis"));
                patientMedicalChart.setDate(resultSet.getDate("dateVisted"));
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
        return patientMedicalChart;
    }
}
