package com.bitcode.studentmanagement.service;

import com.bitcode.studentmanagement.dao.StudentDAO;
import com.bitcode.studentmanagement.model.Student;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void registerStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public Student getStudentDetails(int id) {
        return studentDAO.getStudentById(id);
    }

    public List<Student> getAllStudentDetails() {
        return studentDAO.getAllStudents();
    }

    public void updateStudentResults(int id, int newMarks) {
        studentDAO.updateStudentMarks(id, newMarks);
    }

    public void removeStudent(int id) {
        studentDAO.deleteStudent(id);
    }
}