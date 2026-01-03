package com.faculty.dao;

import com.faculty.model.User;
import com.faculty.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private final DBConnection dbConnection = new DBConnection();




    public boolean addData(User user) throws SQLException {
        String sql = "INSERT INTO sign_up_table (username, password, role) VALUES (?,?,?)";

        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());


            return preparedStatement.executeUpdate() > 0;
        } finally {

        }

    }


    public boolean registerUser(User user) {
        try {
            return addData(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    public boolean loginUser(String username, String password, String role) {
        String sql = "SELECT * FROM sign_up_table WHERE username = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, username.trim());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password").trim();
                String dbRole = rs.getString("role").trim();

                if (dbPassword.equals(password.trim()) && dbRole.equals(role)) {
                    System.out.println("Login successful for user: " + username);
                    return true;
                } else {
                    System.out.println("Password or role mismatch");
                    return false;
                }
            } else {
                System.out.println("No such username in DB");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}
