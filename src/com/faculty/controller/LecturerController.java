package com.faculty.controller;

import com.faculty.view.LecturerDashboardView;
import javax.swing.*;
import java.awt.Color;

public class LecturerController {
    private LecturerDashboardView view;

    public LecturerController(LecturerDashboardView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getProfileSaveButton().addActionListener(e -> saveLecturerProfile());

        view.getBtnUpdateSchedule().addActionListener(e -> {
            view.showSuccessMessage("Teaching Schedule Updated Successfully!");
        });

        view.getBtnAddCourse().addActionListener(e -> {
            String code = view.getTfCourseCode().getText();
            String name = view.getTfCourseName().getText();

            if(!code.isEmpty() && !name.isEmpty()) {
                view.getCourseModel().addRow(new Object[]{code, name});
                view.getTfCourseCode().setText("");
                view.getTfCourseName().setText("");
                view.showSuccessMessage("New Course Added to your List!");
            } else {
                view.showErrorMessage("Please enter both Course Code and Name!");
            }
        });
    }

    private void saveLecturerProfile() {
        JTextField[] fields = view.getProfileFields();
        boolean hasEmpty = false;

        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                field.setBorder(new LecturerDashboardView.DashRoundedBorder(Color.RED, 20));
                hasEmpty = true;
            } else {
                field.setBorder(new LecturerDashboardView.DashRoundedBorder(new Color(140, 82, 255), 20));
            }
        }

        if (hasEmpty) {
            view.showErrorMessage("Please fill all the details!");
            return;
        }

        view.showSuccessMessage("Lecturer Profile Updated Successfully!");
    }
}