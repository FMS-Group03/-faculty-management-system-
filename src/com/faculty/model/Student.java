package com.faculty.model;

public class Student {
    private String studentId, fullName, degree, email;
    private String mobileNumber;

    public Student(String studentId, String fullName, String degree, String email, String mobileNumber) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.degree = degree;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDegree() {
        return degree;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}