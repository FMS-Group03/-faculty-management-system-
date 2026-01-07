package com.faculty.main;

import com.faculty.controller.AdminController;
import com.faculty.controller.LoginController;
import com.faculty.view.LoginView;
import com.faculty.view.AdminDashboardView;

import com.faculty.view.StudentDashboardView;
import com.faculty.controller.StudentController;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            LoginView view = new LoginView();
            new LoginController(view);
        });

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            LoginView view = new LoginView();
            new LoginController(view);
            view.setVisible(true);

//            StudentDashboardView vieww = new StudentDashboardView("Dilshan");
//            new StudentController(vieww);
//            vieww.setVisible(true);

//            AdminDashboardView  View = new AdminDashboardView();
//            new AdminController(View);
        });
        
//        LoginView view = new LoginView();
//        new LoginController(view);
        
        AdminDashboardView view = new AdminDashboardView();
        new AdminController(view);
 
    }
}
