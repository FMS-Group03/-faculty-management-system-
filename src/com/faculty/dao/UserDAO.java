package com.faculty.dao;

import com.faculty.model.Course;
import com.faculty.model.Student;
import com.faculty.model.User;
import com.faculty.util.DBConnection;
import com.faculty.model.Lecturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class UserDAO {

    private final DBConnection dbConnection = new DBConnection();

//Connection for Sign Up and Sign In pages


    public boolean addData(User user) throws SQLException {
        String sql = "INSERT INTO sign_up_table (username, password, role) VALUES (?,?,?)";

        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());


            return preparedStatement.executeUpdate() > 0;
        } finally {

        }

    }


    public boolean registerUser(User user) {
        try {
            return addData(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    public boolean loginUser(String username, String password, String role) {
        String sql = "SELECT * FROM sign_up_table WHERE username = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, username.trim());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password").trim();
                String dbRole = rs.getString("role").trim();

                if (dbPassword.equals(password.trim()) && dbRole.equals(role)) {
                    System.out.println("Login successful for user: " + username);
                    return true;
                } else {
                    System.out.println("Password or role mismatch");
                    return false;
                }
            } else {
                System.out.println("No such username in DB");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    //Database Connection for Student Profile Details



    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student_dashboard (Student_id, Full_name, Degree, Email, Mobile_number) VALUES (?,?,?,?,?)";
        try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
            pst.setString(1, student.getStudentId());
            pst.setString(2, student.getFullName());
            pst.setString(3, student.getDegree());
            pst.setString(4, student.getEmail());
            pst.setString(5, student.getMobileNumber());

            int rows = pst.executeUpdate();
            System.out.println("Insert rows affected: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean studentExists(String studentId) {
        String sql = "SELECT 1 FROM student_dashboard WHERE Student_id=?";
        try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
            pst.setString(1, studentId);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean updateStudent(Student student) {
        String sql = "UPDATE student_dashboard SET Full_name=?, Degree=?, Email=?, Mobile_number=? WHERE Student_id=?";
        try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
            pst.setString(1, student.getFullName());
            pst.setString(2, student.getDegree());
            pst.setString(3, student.getEmail());
            pst.setString(4, student.getMobileNumber());
            pst.setString(5, student.getStudentId());

            int rows = pst.executeUpdate();
            System.out.println("Update rows affected: " + rows);

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }






    //database connnection for Lecture profiles details





    public boolean addLecture(Lecturer lecture) {
        String sql = "INSERT INTO lecture_dashboard (Lecture_Id, Full_Name, Department, Lecture_Email, Lecture_Mobile_Number) VALUES (?,?,?,?,?)";
        try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
            pst.setString(1, lecture.getStudentId());
            pst.setString(2, lecture.getFullName());
            pst.setString(3, lecture.getDepartment());
            pst.setString(4, lecture.getEmail());
            pst.setString(5, lecture.getMobileNumber());

            int rows = pst.executeUpdate();
            System.out.println("Insert rows affected: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean lecturerExists(String lectureId) {
        String sql = "SELECT 1 FROM lecture_dashboard WHERE Lecture_Id=?";
        try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
            pst.setString(1, lectureId);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean updateLecture(Lecturer lecturer) {
        String sql = "UPDATE lecture_dashboard SET Full_Name=?, Department=?, Lecture_Email=?, Lecture_Mobile_Number=? WHERE Lecture_Id=?";
        try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
            pst.setString(1, lecturer.getFullName());
            pst.setString(2, lecturer.getDepartment());
            pst.setString(3, lecturer.getEmail());
            pst.setString(4, lecturer.getMobileNumber());
            pst.setString(5, lecturer.getStudentId());

            int rows = pst.executeUpdate();
            System.out.println("Update rows affected: " + rows);

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    //Course information in lecture dashboard


    public boolean addCourse(String lecturerId, String courseCode, String courseName) {
        String sql = "INSERT INTO lecture_course_details (Lecture_Id, Course_Code, Course_Name) VALUES (?,?,?)";
        try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {
            pst.setString(1, lecturerId);
            pst.setString(2, courseCode);
            pst.setString(3, courseName);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public List<Course> getCoursesByLecturer(String lecturerId) {

        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM lecture_course_details WHERE Lecturer_Id=?";

        try (PreparedStatement pst = dbConnection.prepareStatement(sql)) {

            pst.setString(1, lecturerId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Course c = new Course(
                        rs.getString("Lecturer_Id"),
                        rs.getString("course_code"),
                        rs.getString("course_name")
                );
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



}
