package com.faculty.controller;

import com.faculty.dao.UserDAO;
import com.faculty.model.User;
import com.faculty.view.LoginView;
import com.faculty.view.StudentDashboardView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


 main
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LoginController {

    private final LoginView view;
    private final UserDAO userDAO;

    public LoginController(LoginView view) {
        this.view = view;
        this.userDAO = new UserDAO();
        initController();
    }

    private void initController() {

        // Switch to Sign In
        view.addSignInTabListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.toggleAuthMode("SignIn");
            }
        });

        // Switch to Sign Up
        view.addSignUpTabListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.toggleAuthMode("SignUp");
            }
        });

        // Fix role button styles
        view.addRoleButtonListener(e -> view.updateRoleButtonStyles());

        // Main button (Sign In / Sign Up)
        view.addSignInButtonListener(e -> handleSubmit());
    }

    private void clearSignUpForm() {
        view.getTxtUsername().setText("");
        view.getTxtPassword().setText("");
        view.getTxtConfirmPassword().setText("");

        view.getTglAdmin().setSelected(true);
        view.updateRoleButtonStyles();
    }

    private void handleSubmit() {

        String username = view.getUsernameInput().trim();
        String password = view.getPasswordInput().trim();
        String mode = view.getAuthMode();
        String role = view.getSelectedRole();

        // Common validation
        if (username.isEmpty() || password.isEmpty()) {
            view.showErrorMessage("Username and Password cannot be empty!");
            return;
        }



        if (mode.equals("SignUp")) {
            String confirmPassword = view.getConfirmPasswordInput().trim();

            if (!password.equals(confirmPassword)) {
                view.showErrorMessage("Passwords do not match!");
                return;
            }

            User user = new User(username, password, role);
            boolean success = userDAO.registerUser(user);

            if (success) {
                view.showSuccessMessage("Account created successfully!");
                clearSignUpForm();
            } else {
                view.showErrorMessage("Signup failed (username may exist)");
            }

        }
        // 👉 SIGN IN MODE
        else {
                boolean success = userDAO.loginUser(username, password, role);

                if (success){
                    view.showSuccessMessage("Login successfull Welcome, " + username + " !");
                }else{
                    view.showErrorMessage("Login failed!");
                }
        }
    }
//    private void handleSignIn(String username, String password, String role) {
//        // 1. Create temporary User object for comparison
//        User loginAttempt = new User(username, password, role);
//
//        System.out.println("Attempting Login: " + loginAttempt.toString());
//
//        if (password.length() < 4) {
//            view.showErrorMessage("Invalid Credentials (Password too short)");
//        } else {
//            view.showSuccessMessage("Welcome back, " + username + "!");
//        }
//    }

    //by cs 22010 me
    private void handleSignIn(String username, String password, String role) {
        User loginAttempt = new User(username, password, role);
        System.out.println("Attempting Login: " + loginAttempt.toString());

        if (password.length() < 4) {
            view.showErrorMessage("Invalid Credentials (Password too short)");
            return;
        }

        // Close login window first
        view.dispose();

        // Open the Student Dashboard on the Event Dispatch Thread
        if ("Student".equals(role)) {
            SwingUtilities.invokeLater(() -> {
                new StudentDashboardView(username).setVisible(true);
            });
        } else if ("Admin".equals(role)) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Admin dashboard not implemented yet!");
            });
        } else if ("Lecturer".equals(role)) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Lecturer dashboard not implemented yet!");
            });
        } else {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Invalid Role!");
            });
        }
    }




 main
}
