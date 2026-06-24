package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {

        enrollments.add(
                new Enrollment(1L, "David", 1L)
        );

        enrollments.add(
                new Enrollment(2L, "Tom", 2L)
        );
    }

    // FIND ALL
    public List<Enrollment> findAll() {
        return enrollments;
    }

    // FIND BY ID
    public Enrollment findById(int id) {

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getId() == id) {
                return enrollment;
            }
        }

        return null;
    }

    // CREATE
    public Enrollment create(Enrollment enrollment) {

        enrollments.add(enrollment);

        return enrollment;
    }

    // UPDATE
    public Enrollment update(
            int id,
            Enrollment updatedEnrollment) {

        Enrollment existingEnrollment = findById(id);

        if (existingEnrollment == null) {
            return null;
        }

        existingEnrollment.setStudentName(
                updatedEnrollment.getStudentName()
        );

        existingEnrollment.setCourseId(
                updatedEnrollment.getCourseId()
        );

        return existingEnrollment;
    }

    // DELETE
    public Enrollment deleteById(int id) {

        Enrollment existingEnrollment = findById(id);

        if (existingEnrollment == null) {
            return null;
        }

        enrollments.remove(existingEnrollment);

        return existingEnrollment;
    }
}