package com.faculty.main;

import com.faculty.controller.AdminController;
import com.faculty.controller.LoginController;
import com.faculty.view.LoginView;
import com.faculty.view.AdminDashboardView;

// me
import com.faculty.view.StudentDashboardView;
import com.faculty.controller.StudentController;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {

        //me
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            LoginView view = new LoginView();
            new LoginController(view);
            view.setVisible(true);

//            to show save changes error & success
//            StudentDashboardView vieww = new StudentDashboardView("Dilshan");
//            new StudentController(vieww);
//            vieww.setVisible(true);

//            for admin view
//            AdminDashboardView  View = new AdminDashboardView();
//            new AdminController(View);
        });
    }
}
