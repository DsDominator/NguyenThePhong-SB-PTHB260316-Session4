package com.example.coursemanagement.service;

import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.model.Student;
import com.example.coursemanagement.model.StudentEnrollment;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.StudentEnrollmentRepository;
import com.example.coursemanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentEnrollmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;

    @Autowired
    public StudentEnrollmentService(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            StudentEnrollmentRepository studentEnrollmentRepository) {

        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentEnrollmentRepository = studentEnrollmentRepository;
    }

    public StudentEnrollment enrollStudent(Long studentId, Long courseId) {

        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        StudentEnrollment studentEnrollment = new StudentEnrollment();
        studentEnrollment.setStudent(student);
        studentEnrollment.setCourse(course);

        return studentEnrollmentRepository.save(studentEnrollment);
    }
}