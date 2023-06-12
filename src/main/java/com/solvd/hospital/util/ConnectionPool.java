package com.solvd.hospital.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool connectionPool;
    private int numConnections;
    private List<Connection> connections = new ArrayList<>();

    private ConnectionPool(){

    }
    public static ConnectionPool getInstance(){
        if(connectionPool == null){
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public int getNumConnections() {
        return numConnections;
    }

    public Connection getConnection(){
        Connection connection;
        while(numConnections > 4){
            LOGGER.info("Unable to get connection at this moment.");
        }
        if(connections.isEmpty()) {
            connection = runConnection();
            LOGGER.debug("Connection Successfully Established.");
        }else{
            LOGGER.debug("Successfully reused connection.");
            connection = connections.get(0);
            connections.remove(0);
        }
        addCounter();
        return connection;
    }
    public void releaseConnection(Connection connection){
        connections.add(connection);
        if(numConnections > 0) {
            subtractCounter();
        }
        LOGGER.debug("Connection Successfully released");
    }
    public synchronized void addCounter(){
        numConnections++;
    }
    public synchronized void subtractCounter(){
        numConnections--;
    }

    private Connection runConnection(){
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
