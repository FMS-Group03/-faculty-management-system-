package com.faculty.controller;

import com.faculty.view.LecturerDashboardView;
import javax.swing.*;
import java.awt.Color;
import com.faculty.dao.UserDAO;
import com.faculty.model.Lecturer;
import com.faculty.model.Course;
import java.util.List;

public class LecturerController {
    private LecturerDashboardView view;
    private UserDAO dao;
    private String lecturerId;

    public LecturerController(LecturerDashboardView view, String lecturerId) {
        this.view = view;
        this.lecturerId = lecturerId;
        this.dao = new UserDAO();
        initController();
        loadCourses();
    }

    private void loadCourses() {
        List<Course> courses = dao.getCoursesByLecturer(lecturerId);
        view.getCourseModel().setRowCount(0); // Clear existing rows
        for (Course c : courses) {
            view.getCourseModel().addRow(
                    new Object[]{c.getCourseCode(), c.getCourseName()}
            );
        }
    }

    private void initController() {
        view.getProfileSaveButton().addActionListener(e -> saveLecturerProfile());

        view.getBtnUpdateSchedule().addActionListener(e -> {
            view.showSuccessMessage("Teaching Schedule Updated Successfully!");
        });

        view.getBtnAddCourse().addActionListener(e -> {
            String courseCode = view.getTfCourseCode().getText().trim();
            String courseName = view.getTfCourseName().getText().trim();

            if(courseCode.isEmpty() || courseName.isEmpty()) {
                view.showErrorMessage("Please enter both Course Code and Name!");
                return;
            }

            boolean success = dao.addCourse(lecturerId, courseCode, courseName);

            if(success) {
                view.getCourseModel().addRow(new Object[]{courseCode, courseName});
                view.getTfCourseCode().setText("");
                view.getTfCourseName().setText("");
                view.showSuccessMessage("Course saved successfully!");
            } else {
                view.showErrorMessage("Failed to save course!");
            }
        });
    }

    private void saveLecturerProfile() {
        JTextField[] fields = view.getProfileFields();

        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                view.showErrorMessage("Please fill all the details!");
                return;
            }
        }

        String name = fields[0].getText().trim();
        String id = fields[1].getText().trim();
        String department = fields[2].getText().trim();
        String email = fields[3].getText().trim();
        String mobile = fields[4].getText().trim();

        Lecturer lecturer = new Lecturer(id, name, department, email, mobile);

        boolean success;
        if (dao.lecturerExists(id)) {
            success = dao.updateLecture(lecturer);
        } else {
            success = dao.addLecture(lecturer);
        }

        if (success) {
            view.showSuccessMessage("Lecturer profile saved successfully!");
        } else {
            view.showErrorMessage("Failed to save lecturer profile!");
        }
    }
}