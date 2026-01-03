package com.faculty.controller;

import javax.swing.JOptionPane;

public class LoginController {

    public boolean handleLogin(String username, String password, String role) {
        //  Database
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all fields!");
            return false;
        }


        System.out.println("Attempting login for: " + username + " as " + role);
        return true;
    }

    public void handleSignUp(String username, String password, String role) {

        System.out.println("Creating account for: " + username);
        JOptionPane.showMessageDialog(null, "Registration Successful!");
    }
}