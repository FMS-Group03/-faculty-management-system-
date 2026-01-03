package com.faculty.controller;

import com.faculty.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginController {

    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
        initController();
    }

    private void initController() {

        this.view.addSignInTabListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.toggleAuthMode("SignIn");
            }
        });

        this.view.addSignUpTabListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.toggleAuthMode("SignUp");
            }
        });


        this.view.addRoleButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.updateRoleButtonStyles();
            }
        });
    }
}