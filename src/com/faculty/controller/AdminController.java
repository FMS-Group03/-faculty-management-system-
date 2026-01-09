package com.faculty.controller;

import com.faculty.view.AdminDashboardView;
import com.faculty.view.AdminDashboardView.ManagementPanel;
import com.faculty.view.LoginView;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class AdminController {

    private AdminDashboardView view;

    public AdminController(AdminDashboardView view) {
        this.view = view;
        initController();
        loadDemoData();

        // Set Initial Active Tab
        view.setActiveTab(view.getBtnStudents());
    }

    private void initController() {
        // 1. Sidebar Navigation
        setupSidebarNav(view.getBtnStudents(), "STUDENTS");
        setupSidebarNav(view.getBtnLecturers(), "LECTURERS");
        setupSidebarNav(view.getBtnCourses(), "COURSES");
        setupSidebarNav(view.getBtnDepartments(), "DEPARTMENTS");
        setupSidebarNav(view.getBtnDegrees(), "DEGREES");

        // Logout
        view.addNavListener(view.getBtnLogout(), e -> {
            view.close();
            LoginView loginView = new LoginView();
            new LoginController(loginView);
            loginView.setVisible(true);
        });

<<<<<<< HEAD
        // 2. Attach CRUD Logic
=======
        // 2. CRUD Logic for each Panel
        // Since logic is the same, we reuse the method!
>>>>>>> 9acfdf1f85331d2275884e34b28700e3d39d7b94
        setupCRUDLogic(view.studentPanel, "Student");
        setupCRUDLogic(view.lecturerPanel, "Lecturer");
        setupCRUDLogic(view.coursePanel, "Course");
        setupCRUDLogic(view.deptPanel, "Department");
        setupCRUDLogic(view.degreePanel, "Degree");
    }

    private void setupSidebarNav(JButton btn, String cardName) {
        btn.addActionListener(e -> {
            // Tell the View this button is now Active
            view.setActiveTab(btn);
            view.switchScreen(cardName);
        });
    }

    private void setupCRUDLogic(ManagementPanel panel, String entityName) {
        // ADD
        panel.btnAdd.addActionListener(e -> {
            int colCount = panel.model.getColumnCount();
            String[] emptyRow = new String[colCount];
            emptyRow[0] = "[New]";
            for (int i = 1; i < colCount; i++) emptyRow[i] = "-";
            panel.model.addRow(emptyRow);
            int newRowIdx = panel.model.getRowCount() - 1;
            panel.table.setRowSelectionInterval(newRowIdx, newRowIdx);
        });

        // EDIT (Smart Loop)
        panel.btnEdit.addActionListener(e -> {
            int selectedRow = panel.table.getSelectedRow();
            if (selectedRow != -1) {
                int colCount = panel.model.getColumnCount();
                for (int i = 0; i < colCount; i++) {
                    String colName = panel.model.getColumnName(i);
                    String currentVal = (String) panel.model.getValueAt(selectedRow, i);
                    String newVal = view.showInputDialog("Edit " + colName + ":", currentVal);

                    if (newVal != null && !newVal.trim().isEmpty()) {
                        panel.model.setValueAt(newVal, selectedRow, i);
                    }
                }
            } else {
                view.showMessage("Please select a row to edit.");
            }
        });

        // DELETE
        panel.btnDelete.addActionListener(e -> {
            int selectedRow = panel.table.getSelectedRow();
            if (selectedRow != -1) {
                if (view.showConfirmDialog("Are you sure?")) {
                    panel.model.removeRow(selectedRow);
                }
            } else {
                view.showMessage("Select a row to delete.");
            }
        });

        // SAVE
        panel.btnSave.addActionListener(e -> view.showMessage("Saved!"));
    }

    private void loadDemoData() {
        // Students
        addRows(view.studentPanel.model, new String[][]{
                {"Kumar Sangakkara", "ET/2022/007", "Eng Tech", "kumar@kln.ac.lk", "0771234567"},
                {"Mahela Jayawardene", "ET/2022/008", "Eng Tech", "mahela@kln.ac.lk", "0772345678"}
        });
        // Lecturers
        addRows(view.lecturerPanel.model, new String[][]{
                {"Dr. Kamalinie", "Software Eng", "OOP", "kamalinie@kln.ac.lk", "0711234567"},
                {"Dr. Thilini", "Applied Comp", "Web", "thilini@kln.ac.lk", "0712345678"}
        });
        // Courses
        addRows(view.coursePanel.model, new String[][]{
                {"ETEC 21062", "OOP", "2", "Dr. Kamalinie"},
                {"ETEC 31053", "DSA", "3", "Dr. Kamalinie"}
        });
        // Departments
        addRows(view.deptPanel.model, new String[][]{
                {"Software Eng", "Dr. Sidath", "IT", "15"},
                {"Applied Comp", "Dr. Thilini", "BET", "12"}
        });
        // Degrees
        addRows(view.degreePanel.model, new String[][]{
                {"Eng Technology", "Applied Comp", "375"},
                {"Info Technology", "Software Eng", "375"}
        });
    }
<<<<<<< HEAD

    private void addRows(DefaultTableModel model, String[][] data) {
        for (String[] row : data) model.addRow(row);
    }
=======
>>>>>>> 9acfdf1f85331d2275884e34b28700e3d39d7b94
}