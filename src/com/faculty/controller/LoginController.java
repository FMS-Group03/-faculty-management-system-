package com.faculty.controller;

import com.faculty.model.User;
import com.faculty.view.AdminDashboardView;
import com.faculty.view.LoginView;
import com.faculty.view.StudentDashboardView;
<<<<<<< HEAD
import com.faculty.view.LecturerDashboardView;
import com.faculty.controller.LecturerController;

import javax.swing.*;
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
>>>>>>> 9acfdf1f85331d2275884e34b28700e3d39d7b94
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginController {

    private LoginView view;
<<<<<<< HEAD
    private UserDAO userDAO;
=======

    private boolean isSignUpMode = false;
>>>>>>> 9acfdf1f85331d2275884e34b28700e3d39d7b94

    public LoginController(LoginView view) {
        this.view = view;
        initController();
    }

    private void initController() {
<<<<<<< HEAD
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
=======

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
>>>>>>> 9acfdf1f85331d2275884e34b28700e3d39d7b94
        String username = view.getUsernameInput();
        String password = view.getPasswordInput();
        String role = view.getSelectedRole();
        String mode = view.getAuthMode();

<<<<<<< HEAD
=======

>>>>>>> 9acfdf1f85331d2275884e34b28700e3d39d7b94
        if (username.isEmpty() || password.isEmpty()) {
            view.showErrorMessage("Please enter username and password.");
            return;
        }

<<<<<<< HEAD
        if (mode.equals("SignUp")) {
            // TEAM'S REGISTER LOGIC
            User newUser = new User(username, password, role);
            if (userDAO.registerUser(newUser)) {
                view.showSuccessMessage("Account created! Please Sign In.");
                view.toggleAuthMode("SignIn");
            } else {
                view.showErrorMessage("Signup Failed. Username might exist.");
            }
=======
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
>>>>>>> 9acfdf1f85331d2275884e34b28700e3d39d7b94
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

<<<<<<< HEAD
            case "Student":
            case "STUDENT":
                // --- FIX IS HERE ---
                // We pass 'username' because StudentDashboardView requires it!
                StudentDashboardView studentView = new StudentDashboardView(username);
                studentView.setVisible(true);
                break;

            case "Lecturer":
            case "LECTURER":
                LecturerDashboardView lecturerView = new LecturerDashboardView(username);
                new LecturerController(lecturerView, username);
                lecturerView.setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Unknown Role: " + role);
        }
    }
}
=======
}
>>>>>>> 9acfdf1f85331d2275884e34b28700e3d39d7b94
