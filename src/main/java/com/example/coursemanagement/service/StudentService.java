package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.request.StudentCreateRequest;
import com.example.coursemanagement.model.Student;
import com.example.coursemanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(StudentCreateRequest req) {

        Student student = new Student();
        student.setName(req.getName());
        student.setEmail(req.getEmail());

        return studentRepository.save(student);
    }
}