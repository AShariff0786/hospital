package com.solvd.hospital.dao.impl;

import com.solvd.hospital.dao.INurseDao;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NurseDao implements INurseDao {
    private final static Logger LOGGER = LogManager.getLogger(NurseDao.class);
    private static final String INSERT = "INSERT INTO Nurse (name, position, employeeId, Departments_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Nurse SET name=?, position=? WHERE employeeId=?";
    private static final String DELETE = "DELETE FROM Nurse WHERE employeeId=?";
    private static final String GET = "SELECT * FROM Nurse WHERE employeeId=?";
    @Override
    public void insert(Nurse nurse) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement =  connection.prepareStatement(INSERT);) {
            preparedStatement.setString(1, nurse.getName());
            preparedStatement.setString(2, nurse.getPosition());
            preparedStatement.setInt(3, nurse.getId());
            preparedStatement.setInt(4,nurse.getDepartment().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Nurse nurse) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);){
            preparedStatement.setString(1, nurse.getName());
            preparedStatement.setString(2, nurse.getPosition());
            preparedStatement.setInt(3, nurse.getId());
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
    public Nurse getById(int id) {
        Nurse nurse = new Nurse();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET);){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    nurse.setId(resultSet.getInt("employeeId"));
                    nurse.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return nurse;
    }
}
