package com.sms.view;

import java.util.Scanner;

import com.sms.controller.StudentController;
import com.sms.model.Student;

public class StudentView {

    private final StudentController controller;
    private final Scanner sc = new Scanner(System.in);

    public StudentView(StudentController controller) {
        this.controller = controller;
    }

    
    private Student readStudent(int id) {

        System.out.print("Student Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Department: ");
        String department = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();

        return new Student(id, name, email, department, phone);
    }

    
    public void start() {

        while (true) {
            try {
                System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("0. Exit");

                System.out.print("Choose: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Student ID: ");
                        int newId = Integer.parseInt(sc.nextLine());

                        boolean added = controller.add(readStudent(newId));

                        System.out.println(
                            added ? "Student added successfully."
                                  : "Failed to add student."
                        );
                        break;

                    case 2:
                        if (controller.getAll().isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            controller.getAll().forEach(System.out::println);
                        }
                        break;

                    case 3:
                        System.out.print("Student ID: ");
                        int searchId = Integer.parseInt(sc.nextLine());

                        Student found = controller.getById(searchId);

                        System.out.println(
                            found == null ? "Student not found." : found
                        );
                        break;

                    case 4:
                        System.out.print("ID to update: ");
                        int updateId = Integer.parseInt(sc.nextLine());

                        System.out.println(controller.update(updateId, readStudent(updateId)) ? "student updated.":"student not found or already logined");
                        break;

                    case 5:
                        System.out.print("ID to delete: ");
                        int deleteId = Integer.parseInt(sc.nextLine());
                        System.out.println(controller.delete(Integer.parseInt(sc.nextLine())) ? "student deleted." : "student not found.");
                        break;

                    case 0:
                        System.out.println("Thank you! Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
