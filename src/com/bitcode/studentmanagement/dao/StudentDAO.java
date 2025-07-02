package com.bitcode.studentmanagement.dao;

import com.bitcode.studentmanagement.model.Student;
import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);
    Student getStudentById(int id);
    List<Student> getAllStudents();
    void updateStudentMarks(int id, int newMarks);
    void deleteStudent(int id);
}