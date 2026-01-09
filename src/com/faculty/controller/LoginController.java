package com.faculty.controller;

import com.faculty.dao.UserDAO;
import com.faculty.model.User;
import com.faculty.view.AdminDashboardView;
import com.faculty.view.LoginView;
import com.faculty.view.StudentDashboardView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginController {

    private LoginView view;
    private UserDAO userDAO;

    public LoginController(LoginView view) {
        this.view = view;
        this.userDAO = new UserDAO();
        initController();
    }

    private void initController() {
        // Tab Switching
        view.addSignInTabListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { view.toggleAuthMode("SignIn"); }
        });

        view.addSignUpTabListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { view.toggleAuthMode("SignUp"); }
        });

        view.addRoleButtonListener(e -> view.updateRoleButtonStyles());

        // Main Button Logic
        view.addSignInButtonListener(e -> handleSubmit());
    }

    private void handleSubmit() {
        String username = view.getUsernameInput();
        String password = view.getPasswordInput();
        String role = view.getSelectedRole();
        String mode = view.getAuthMode();

        if (username.isEmpty() || password.isEmpty()) {
            view.showErrorMessage("Please enter username and password.");
            return;
        }

        if (mode.equals("SignUp")) {
            // TEAM'S REGISTER LOGIC
            User newUser = new User(username, password, role);
            if (userDAO.registerUser(newUser)) {
                view.showSuccessMessage("Account created! Please Sign In.");
                view.toggleAuthMode("SignIn");
            } else {
                view.showErrorMessage("Signup Failed. Username might exist.");
            }
        } else {
            // TEAM'S LOGIN LOGIC
            boolean isValid = userDAO.loginUser(username, password, role);

            if (isValid) {
                view.showSuccessMessage("Login Successful! Welcome, " + username);
                view.dispose();

                // PASS USERNAME TO OPEN DASHBOARD
                openDashboard(username, role);
            } else {
                view.showErrorMessage("Login Failed! Check Username, Password, or Role.");
            }
        }
    }

    // --- UPDATED METHOD ---
    private void openDashboard(String username, String role) {
        switch (role) {
            case "Admin":
            case "ADMIN":
                AdminDashboardView adminView = new AdminDashboardView();
                new AdminController(adminView);
                adminView.setVisible(true);
                break;

            case "Student":
            case "STUDENT":
                // --- FIX IS HERE ---
                // We pass 'username' because StudentDashboardView requires it!
                StudentDashboardView studentView = new StudentDashboardView(username);
                studentView.setVisible(true);
                break;

            case "Lecturer":
            case "LECTURER":
                JOptionPane.showMessageDialog(null, "Lecturer Dashboard Coming Soon!");
                break;

            default:
                JOptionPane.showMessageDialog(null, "Unknown Role: " + role);
        }
    }
}