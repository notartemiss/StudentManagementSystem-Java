package com.bitcode.studentmanagement.dao;

import com.bitcode.studentmanagement.model.Student;
import com.bitcode.studentmanagement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, email, marks) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setInt(3, student.getMarks());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    @Override
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Student student = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("marks")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error getting student by ID: " + e.getMessage());
        }

        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("marks")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error getting all students: " + e.getMessage());
        }

        return students;
    }

    @Override
    public void updateStudentMarks(int id, int newMarks) {
        String sql = "UPDATE students SET marks = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, newMarks);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating student marks: " + e.getMessage());
        }
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }
}