package com.faculty.main;

import com.faculty.controller.AdminController;
import com.faculty.controller.LoginController;
import com.faculty.view.AdminDashboardView;
import com.faculty.view.LoginView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
 CS_2022_042
        SwingUtilities.invokeLater(() -> {
            LoginView view = new LoginView();
            new LoginController(view);
        });


//        LoginView view = new LoginView();
//
//        new LoginController(view);
        AdminDashboardView view = new AdminDashboardView();
        new AdminController(view);
 main
    }
}
