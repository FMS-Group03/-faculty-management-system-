package com.faculty.controller;

import javax.swing.JOptionPane;

public class LoginController {

    public boolean handleLogin(String username, String password, String role) {
        // මෙහිදී Database එක පරීක්ෂා කරන (DAO) logic එක ලියන්න
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all fields!");
            return false;
        }

        // උදාහරණයක් ලෙස:
        System.out.println("Attempting login for: " + username + " as " + role);
        return true;
    }

    public void handleSignUp(String username, String password, String role) {
        // මෙහිදී අලුත් User කෙනෙකු ඇතුළත් කිරීමේ logic එක ලියන්න
        System.out.println("Creating account for: " + username);
        JOptionPane.showMessageDialog(null, "Registration Successful!");
    }
}