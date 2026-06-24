package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
import com.example.coursemanagement.dto.request.StudentEnrollmentRequest;
import com.example.coursemanagement.service.StudentEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students-enrollments")
public class StudentEnrollmentController {

    private final StudentEnrollmentService studentEnrollmentService;

    @Autowired
    public StudentEnrollmentController(
            StudentEnrollmentService studentEnrollmentService) {

        this.studentEnrollmentService = studentEnrollmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> enrollStudent(
            @RequestBody StudentEnrollmentRequest req) {

        try {
            studentEnrollmentService.enrollStudent(
                    req.getStudentId(),
                    req.getCourseId()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            true,
                            "Enroll student successfully",
                            null
                    ));

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    ));
        }
    }
}