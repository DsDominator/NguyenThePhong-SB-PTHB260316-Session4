package com.example.coursemanagement.service;

import com.example.coursemanagement.model.Enrollment;
import com.example.coursemanagement.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(
            EnrollmentRepository enrollmentRepository) {

        this.enrollmentRepository = enrollmentRepository;
    }

    // GET ALL
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // GET BY ID
    public Enrollment getEnrollmentById(int id) {
        return enrollmentRepository.findById(id);
    }

    // CREATE
    public Enrollment createEnrollment(
            Enrollment enrollment) {

        return enrollmentRepository.create(enrollment);
    }

    // UPDATE
    public Enrollment updateEnrollment(
            int id,
            Enrollment updatedEnrollment) {

        return enrollmentRepository.update(
                id,
                updatedEnrollment
        );
    }

    // DELETE
    public Enrollment deleteEnrollmentById(int id) {

        return enrollmentRepository.deleteById(id);
    }
}