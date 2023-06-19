package com.solvd.hospital.dao.impl;

import com.solvd.hospital.dao.IAppointmentDao;
import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;


public class AppointmentDao implements IAppointmentDao {
    private final static Logger LOGGER = LogManager.getLogger(AppointmentDao.class);
    private static final String INSERT = "INSERT INTO Appointment (date, time, Patient_id, " +
            "Doctor_employeeId, Nurse_employeeId, id) VALUES (?,?,?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Appointment SET date=?, time=? WHERE id=?";
    private static final String DELETE = "DELETE FROM Appointment WHERE id=?";
    private static final String GET = "SELECT * FROM Appointment WHERE id=?";
    @Override
    public void insert(Appointment appointment) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT);) {
            preparedStatement.setDate(1, (java.sql.Date) appointment.getDate());
            preparedStatement.setTime(2, Time.valueOf(appointment.getTime()));
            preparedStatement.setInt(3, appointment.getPatient().getId());
            preparedStatement.setInt(4, appointment.getDoctor().getId());
            preparedStatement.setInt(5, appointment.getNurse().getId());
            preparedStatement.setInt(6, appointment.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Appointment appointment) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setDate(1, (java.sql.Date) appointment.getDate());
            preparedStatement.setTime(2, Time.valueOf(appointment.getTime()));
            preparedStatement.setInt(3, appointment.getId());
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
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
    public Appointment getById(int id) {
        Appointment appointment=new Appointment();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET);) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {
                    appointment.setId(resultSet.getInt("id"));
                    appointment.setDate(resultSet.getDate("date"));
                    appointment.setTime(resultSet.getTime("time").toLocalTime());
                    appointment.setDoctor(new Doctor((resultSet.getInt("Doctor_employeeId"))));
                    appointment.setNurse(new Nurse(resultSet.getInt("Nurse_employeeId")));
                    appointment.setPatient(new Patient(resultSet.getInt("Patient_id")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return appointment;
    }
}
