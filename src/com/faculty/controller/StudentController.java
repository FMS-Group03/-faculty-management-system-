package com.faculty.controller;

import com.faculty.dao.UserDAO;
import com.faculty.model.Student;
import com.faculty.view.StudentDashboardView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentController {

    private StudentDashboardView view;
    private UserDAO dao;

    public StudentController(StudentDashboardView view) {
        this.view = view;
        this.dao = new UserDAO();


        initController();
    }


    private void initController() {
        view.getProfileSaveButton().addActionListener(e -> saveProfile());
    }

    // Save profile method
    private void saveProfile() {

        String name   = view.getTfName().getText().trim();
        String id     = view.getTfId().getText().trim();
        String degree = view.getTfDegree().getText().trim();
        String email  = view.getTfEmail().getText().trim();
        String mobile = view.getTfMobile().getText().trim();

        // Validation
        if (name.isEmpty() || id.isEmpty()) {
            view.showErrorMessage("Name and Student ID are required!");
            return;
        }

        Student student = new Student(id, name, degree, email, mobile);

        boolean success;

        if (dao.studentExists(id)) {
            success = dao.updateStudent(student);
        } else {
            success = dao.addStudent(student);
        }

        if (success) {
            view.showSuccessMessage("Profile saved successfully!");
        } else {
            view.showErrorMessage("Failed to save profile!");
        }
    }
}