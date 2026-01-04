package com.faculty.controller;

 CS_2022_042
public class AdminController {
}

import com.faculty.view.AdminDashboardView;
import com.faculty.view.AdminDashboardView.ManagementPanel;
import com.faculty.view.LoginView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class AdminController {

    private AdminDashboardView view;

    public AdminController(AdminDashboardView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // 1. Sidebar Navigation Logic
        setupSidebarNav(view.getBtnStudents(), "STUDENTS");
        setupSidebarNav(view.getBtnLecturers(), "LECTURERS");
        setupSidebarNav(view.getBtnCourses(), "COURSES");
        setupSidebarNav(view.getBtnDepartments(), "DEPARTMENTS");
        setupSidebarNav(view.getBtnDegrees(), "DEGREES");

        view.addNavListener(view.getBtnLogout(), e -> {
            view.close();
            // In real app: new LoginController(new LoginView());
            System.out.println("Logged out");
        });

        // 2. CRUD Logic for each Panel
        // Since logic is the same, we reuse the metho
        setupCRUDLogic(view.studentPanel, "Student");
        setupCRUDLogic(view.lecturerPanel, "Lecturer");
        setupCRUDLogic(view.coursePanel, "Course");
        setupCRUDLogic(view.deptPanel, "Department");
        setupCRUDLogic(view.degreePanel, "Degree");
    }

    private void setupSidebarNav(JButton btn, String cardName) {
        view.addNavListener(btn, e -> {
            resetSidebarStyles();
            btn.setBackground(Color.WHITE);
            btn.setForeground(new Color(124, 77, 255));
            view.switchScreen(cardName);
        });
    }

    private void resetSidebarStyles() {
        Color purple = new Color(124, 77, 255);
        resetBtn(view.getBtnStudents(), purple);
        resetBtn(view.getBtnLecturers(), purple);
        resetBtn(view.getBtnCourses(), purple);
        resetBtn(view.getBtnDepartments(), purple);
        resetBtn(view.getBtnDegrees(), purple);
    }

    private void resetBtn(JButton btn, Color c) {
        btn.setBackground(c);
        btn.setForeground(Color.WHITE);
    }

    /**
     * Attaches Generic Add/Edit/Delete logic to a panel
     */
    private void setupCRUDLogic(ManagementPanel panel, String entityName) {

        // --- ADD ---
        panel.btnAdd.addActionListener(e -> {
            int colCount = panel.model.getColumnCount();
            String[] emptyRow = new String[colCount];
            emptyRow[0] = "[New " + entityName + "]"; // Placeholder
            for (int i = 1; i < colCount; i++) emptyRow[i] = "-";

            panel.model.addRow(emptyRow);
            view.showMessage("New row added to " + entityName + "s");
        });

        // --- EDIT ---
        panel.btnEdit.addActionListener(e -> {
            int selectedRow = panel.table.getSelectedRow();
            if (selectedRow != -1) {
                String currentVal = (String) panel.model.getValueAt(selectedRow, 0);
                String newVal = view.showInputDialog("Edit " + entityName + " Name:", currentVal);

                if (newVal != null && !newVal.trim().isEmpty()) {
                    panel.model.setValueAt(newVal, selectedRow, 0);
                }
            } else {
                view.showMessage("Please select a " + entityName + " to edit.");
            }
        });

        // --- DELETE ---
        panel.btnDelete.addActionListener(e -> {
            int selectedRow = panel.table.getSelectedRow();
            if (selectedRow != -1) {
                if (view.showConfirmDialog("Are you sure you want to delete this " + entityName + "?")) {
                    panel.model.removeRow(selectedRow);
                }
            } else {
                view.showMessage("Please select a row to delete.");
            }
        });

        // --- SAVE ---
        panel.btnSave.addActionListener(e -> {
            view.showMessage("Data saved to Database successfully!");
            // Here you would call DAO.save(panel.getData()...)
        });
    }
}

