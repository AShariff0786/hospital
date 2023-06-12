package com.solvd.hospital.dao.impl;

import com.solvd.hospital.dao.IPatientMedicalChartDao;
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
    private final static String GET = "SELECT * FROM PatientMedicalChart WHERE reportId=?";
    @Override
    public void insert(PatientMedicalChart patientMedicalChart) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT);){
            preparedStatement.setString(1, patientMedicalChart.getDiagnosis());
            preparedStatement.setDate(2, (java.sql.Date) patientMedicalChart.getDate());
            preparedStatement.setInt(3, patientMedicalChart.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(PatientMedicalChart patientMedicalChart) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setString(1, patientMedicalChart.getDiagnosis());
            preparedStatement.setDate(2, (java.sql.Date) patientMedicalChart.getDate());
            preparedStatement.setInt(3, patientMedicalChart.getId());
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
    public PatientMedicalChart getById(int id) {
        PatientMedicalChart patientMedicalChart=new PatientMedicalChart();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET);){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    patientMedicalChart.setId(resultSet.getInt("reportId"));
                    patientMedicalChart.setDiagnosis(resultSet.getString("diagnosis"));
                    patientMedicalChart.setDate(resultSet.getDate("dateVisted"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return patientMedicalChart;
    }
}
