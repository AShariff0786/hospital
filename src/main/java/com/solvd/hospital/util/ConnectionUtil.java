package com.solvd.hospital.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionUtil.class);

    public Connection runConnection(){
        Connection connection;
        Properties properties = PropertiesUtil.getProperties();;
        try {
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.user"), properties.getProperty("db.password"));
            LOGGER.debug("Connection Successfully Established.");
        } catch (SQLException e) {
            LOGGER.error("Unable to get connection.");
            throw new RuntimeException(e);
        }
        return connection;
    }

}
