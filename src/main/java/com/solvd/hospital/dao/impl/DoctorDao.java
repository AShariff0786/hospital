package com.solvd.hospital.dao.impl;

import com.solvd.hospital.dao.IDoctorDao;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDao implements IDoctorDao {
    private final static Logger LOGGER = LogManager.getLogger(DoctorDao.class);
    private static final String INSERT = "INSERT INTO Doctor (employeeId, name, position, Departments_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Doctor SET name=?, position=? WHERE employeeId=?";
    private static final String DELETE = "DELETE FROM Doctor WHERE employeeId=?";
    private static final String GET = "SELECT * FROM Doctor WHERE employeeId=?";


    @Override
    public void insert(Doctor doctor) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement =  connection.prepareStatement(INSERT);) {
            preparedStatement.setInt(1, doctor.getId());
            preparedStatement.setString(2, doctor.getName());
            preparedStatement.setString(3, doctor.getPosition());
            preparedStatement.setInt(4,doctor.getDepartment().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Doctor doctor) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getPosition());
            preparedStatement.setInt(3, doctor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Doctor getById(int id) {
        Doctor doctor = new Doctor();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET);){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    doctor.setId(resultSet.getInt("employeeId"));
                    doctor.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return doctor;
    }
}
