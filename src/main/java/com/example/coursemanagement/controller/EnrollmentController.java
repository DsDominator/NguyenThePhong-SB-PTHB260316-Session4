package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
import com.example.coursemanagement.model.Enrollment;
import com.example.coursemanagement.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(
            EnrollmentService enrollmentService) {

        this.enrollmentService = enrollmentService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>>
    getAllEnrollments() {

        ApiResponse<List<Enrollment>> response =
                new ApiResponse<>(
                        true,
                        "Get all enrollments successfully",
                        enrollmentService.getAllEnrollments()
                );

        return ResponseEntity.ok(response);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>>
    getEnrollmentById(@PathVariable int id) {

        Enrollment enrollment =
                enrollmentService.getEnrollmentById(id);

        if (enrollment == null) {

            ApiResponse<?> response =
                    new ApiResponse<>(
                            false,
                            "Enrollment not found",
                            null
                    );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        ApiResponse<Enrollment> response =
                new ApiResponse<>(
                        true,
                        "Get enrollment successfully",
                        enrollment
                );

        return ResponseEntity.ok(response);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>>
    createEnrollment(
            @RequestBody Enrollment enrollment) {

        Enrollment createdEnrollment =
                enrollmentService.createEnrollment(enrollment);

        ApiResponse<Enrollment> response =
                new ApiResponse<>(
                        true,
                        "Create enrollment successfully",
                        createdEnrollment
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>>
    updateEnrollment(
            @PathVariable int id,
            @RequestBody Enrollment updatedEnrollment) {

        Enrollment enrollment =
                enrollmentService.updateEnrollment(
                        id,
                        updatedEnrollment
                );

        if (enrollment == null) {

            ApiResponse<?> response =
                    new ApiResponse<>(
                            false,
                            "Enrollment not found",
                            null
                    );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        ApiResponse<Enrollment> response =
                new ApiResponse<>(
                        true,
                        "Update enrollment successfully",
                        enrollment
                );

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>>
    deleteEnrollment(@PathVariable int id) {

        Enrollment enrollment =
                enrollmentService.deleteEnrollmentById(id);

        if (enrollment == null) {

            ApiResponse<?> response =
                    new ApiResponse<>(
                            false,
                            "Enrollment not found",
                            null
                    );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        ApiResponse<?> response =
                new ApiResponse<>(
                        true,
                        "Delete enrollment successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }
}