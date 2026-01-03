package com.faculty.main;

import com.faculty.controller.AdminController;
import com.faculty.controller.LoginController;
import com.faculty.view.AdminDashboardView;
import com.faculty.view.LoginView;

public class Main {
    public static void main(String[] args) {

//        LoginView view = new LoginView();
//
//        new LoginController(view);
        AdminDashboardView view = new AdminDashboardView();
        new AdminController(view);
    }
}