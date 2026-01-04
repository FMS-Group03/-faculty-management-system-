package com.faculty.controller;

import com.faculty.model.User;
import com.faculty.view.LoginView;
import com.faculty.view.StudentDashboardView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LoginController {

    private LoginView view;

    private boolean isSignUpMode = false;

    public LoginController(LoginView view) {
        this.view = view;
        initController();
    }

    private void initController() {

        this.view.addSignInTabListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isSignUpMode = false; // Update state
                view.toggleAuthMode("SignIn");
            }
        });

        this.view.addSignUpTabListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isSignUpMode = true;
                view.toggleAuthMode("SignUp");
            }
        });


        this.view.addRoleButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.updateRoleButtonStyles();
            }
        });

        this.view.addSignInButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAuthAction();
            }
        });
    }

    private void handleAuthAction() {
        String username = view.getUsernameInput();
        String password = view.getPasswordInput();
        String role = view.getSelectedRole();


        if (username.isEmpty() || password.isEmpty()) {
            view.showErrorMessage("Username and Password cannot be empty!");
            return;
        }

        if (isSignUpMode) {
            handleSignUp(username, password, role);
        } else {
            handleSignIn(username, password, role);
        }
    }

    private void handleSignUp(String username, String password, String role) {
        String confirmPass = view.getConfirmPasswordInput();

        if (!password.equals(confirmPass)) {
            view.showErrorMessage("Passwords do not match!");
            return;
        }

        User newUser = new User(username, password, role);

        System.out.println("Registering: " + newUser.toString());

        view.showSuccessMessage("Account created successfully for " + role + "!");

        isSignUpMode = false;
        view.toggleAuthMode("SignIn");
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


}
