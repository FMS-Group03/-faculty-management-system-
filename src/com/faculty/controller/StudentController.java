package com.faculty.controller;

import com.faculty.view.StudentDashboardView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentController {

    private StudentDashboardView view;

    public StudentController(StudentDashboardView view) {
        this.view = view;
        initController();
    }

    // Initialize all listeners
    private void initController() {
        view.getProfileSaveButton().addActionListener(e -> saveProfile());
    }

    // Save profile method
    private void saveProfile() {
        JTextField[] fields = view.getProfileFields();
        boolean hasEmpty = false;

        // Reset all borders to default
        for (JTextField field : fields) {
            field.setBorder(new StudentDashboardView.DashRoundedBorder(view.getBtnProfile().getBackground(), 20));
        }

        // Check each field for empty value
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                // Highlight empty fields
                field.setBorder(new StudentDashboardView.DashRoundedBorder(java.awt.Color.RED, 20));
                hasEmpty = true;
            }
        }

        if (hasEmpty) {
            view.showErrorMessage("All profile details must be filled!");
            // Focus the first empty field
            for (JTextField field : fields) {
                if (field.getText().trim().isEmpty()) {
                    field.requestFocus();
                    break;
                }
            }
            return;
        }
        view.showSuccessMessage("Profile updated successfully!");
    }
}
