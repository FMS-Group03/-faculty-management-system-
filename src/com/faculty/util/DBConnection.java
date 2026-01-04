package com.faculty.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private String url = "jdbc:mysql://localhost:3306/oop_group03_project";
    private String username = "root";
    private String password = "";

    private Connection connection;

    // Constructor
    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver
            connection = DriverManager.getConnection(url, username, password); // Initialize connection
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Getter to provide connection to other classes
    public Connection getConnection() {
        return connection;
    }
}
