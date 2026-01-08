package com.faculty.model;

public class Course {

    private String lecturerId;
    private String courseCode;
    private String courseName;

    public Course(String lecturerId, String courseCode, String courseName) {
        this.lecturerId = lecturerId;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }
}
