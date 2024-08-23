package com.mycompany.projectloginoverview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for managing the connection to the MySQL database. This class handles
 * the process of connecting to and disconnecting from the database.
 */
public class DatabaseConnection {
    // URL for connecting to the MySQL database. It specifies the protocol (jdbc:mysql), 
    // the hostname (localhost), the port number (3306), and the database name (mydatabase).

    private static final String url = "jdbc:mysql://localhost:3306/mydatabase"; // Database URL including database name
    private static final String user = "root";
    // userfor connecting to the database. This is usually set up during the database configuration.
    private static final String password = "root"; // MySQL password

    /**
     * Gets a connection to the MySQL database. This method loads the JDBC
     * driver and establishes a connection using the provided URL, username, and
     * password.
     *
     * @return Connection object used to interact with the database
     */
    /*
    The MySQL JDBC driver is a Java library that allows Java applications to connect to a MySQL database using the JDBC (Java Database Connectivity) API. 
    JDBC is a standard API in Java for interacting with relational databases.
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver. This class is required for JDBC to interact with MySQL databases.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection to the database using the URL, username, and password.
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected to the database.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection to the database failed.");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Closes the provided database connection.
     *
     * @param connection Connection object to be closed
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                // Close the database connection. This is important to free up resources and avoid memory leaks.
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the connection.");
                e.printStackTrace();
            }
        }
    }
}
