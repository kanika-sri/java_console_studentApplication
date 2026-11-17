
package com.sms.service;

import java.util.List;

import com.sms.model.Student;
import com.sms.repository.StudentRepository;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // Constructor Injection
    public StudentServiceImpl(StudentRepository studentRepository) {
        if (studentRepository == null) {
            throw new IllegalArgumentException(
                "StudentRepository cannot be null"
            );
        }
        this.studentRepository = studentRepository;
    }

    // Add Student
    @Override
    public boolean addStudent(Student student) {
        validate(student);
        return studentRepository.add(student);
    }

    // Get All Students
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get Student By ID
    @Override
    public Student getStudentById(int id) {
        validateId(id);
        return studentRepository.findById(id);
    }

    // Update Student
    @Override
    public boolean updateStudent(int id, Student student) {
        validateId(id);
        validate(student);

        return studentRepository.update(id, student);
    }

    // Delete Student
    @Override
    public boolean deleteStudent(int id) {
        validateId(id);
        return studentRepository.delete(id);
    }

    // Validate Student
    private void validate(Student student) {
        if (student == null) {
            throw new IllegalArgumentException(
                "Student cannot be null"
            );
        }
    }

    // Validate ID
    private void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                "Student ID must be positive"
            );
        }
    }
}
