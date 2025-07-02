package com.bitcode.studentmanagement;

import com.bitcode.studentmanagement.dao.StudentDAO;
import com.bitcode.studentmanagement.dao.StudentDAOImpl;
import com.bitcode.studentmanagement.model.Student;
import com.bitcode.studentmanagement.service.StudentService;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAOImpl();
        StudentService studentService = new StudentService(studentDAO);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student by ID");
            System.out.println("4. Update Student Marks");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(studentService, scanner);
                    break;
                case 2:
                    viewAllStudents(studentService);
                    break;
                case 3:
                    viewStudentById(studentService, scanner);
                    break;
                case 4:
                    updateStudentMarks(studentService, scanner);
                    break;
                case 5:
                    deleteStudent(studentService, scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(StudentService service, Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        System.out.print("Enter student marks: ");
        int marks = scanner.nextInt();

        Student student = new Student(0, name, email, marks);
        service.registerStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void viewAllStudents(StudentService service) {
        List<Student> students = service.getAllStudentDetails();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\nList of all students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void viewStudentById(StudentService service, Scanner scanner) {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        Student student = service.getStudentDetails(id);
        if (student != null) {
            System.out.println("\nStudent details:");
            System.out.println(student);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }

    private static void updateStudentMarks(StudentService service, Scanner scanner) {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter new marks: ");
        int newMarks = scanner.nextInt();

        service.updateStudentResults(id, newMarks);
        System.out.println("Marks updated successfully!");
    }

    private static void deleteStudent(StudentService service, Scanner scanner) {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();

        service.removeStudent(id);
        System.out.println("Student deleted successfully!");
    }
}