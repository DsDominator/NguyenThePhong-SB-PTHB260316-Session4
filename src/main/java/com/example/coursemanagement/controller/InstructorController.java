package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
import com.example.coursemanagement.dto.request.InstructorCreateRequest;
import com.example.coursemanagement.model.Instructor;
import com.example.coursemanagement.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllInstructors() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Get all instructors successfully",
                        instructorService.findAllInstructors()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getInstructorById(
            @PathVariable Long id) {

        try {
            Instructor instructor =
                    instructorService.findInstructorById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Get instructor successfully",
                            instructor
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    ));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createInstructor(
            @RequestBody InstructorCreateRequest req) {

        Instructor createdInstructor =
                instructorService.createInstructor(req);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Create instructor successfully",
                        createdInstructor
                ));
    }
}