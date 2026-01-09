package com.faculty.main;

import com.faculty.controller.AdminController;
import com.faculty.controller.LoginController;
import com.faculty.controller.StudentController;
import com.faculty.view.LoginView;
import com.faculty.view.AdminDashboardView;


import com.faculty.view.StudentDashboardView;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {

<<<<<<< HEAD
=======
        //me
>>>>>>> 9acfdf1f85331d2275884e34b28700e3d39d7b94
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Only create one LoginView
            LoginView loginView = new LoginView();
            new LoginController(loginView);
            loginView.setVisible(true);

        });
    }
}