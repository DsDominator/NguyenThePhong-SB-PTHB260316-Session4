package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
import com.example.coursemanagement.model.Instructor;
import com.example.coursemanagement.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(
            InstructorService instructorService) {

        this.instructorService = instructorService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>>
    getAllInstructors() {

        ApiResponse<List<Instructor>> response =
                new ApiResponse<>(
                        true,
                        "Get all instructors successfully",
                        instructorService.getAllInstructors()
                );

        return ResponseEntity.ok(response);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>>
    getInstructorById(@PathVariable int id) {

        Instructor instructor =
                instructorService.getInstructorById(id);

        // NOT FOUND
        if (instructor == null) {

            ApiResponse<?> response =
                    new ApiResponse<>(
                            false,
                            "Instructor not found",
                            null
                    );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        // SUCCESS
        ApiResponse<Instructor> response =
                new ApiResponse<>(
                        true,
                        "Get instructor successfully",
                        instructor
                );

        return ResponseEntity.ok(response);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>>
    createInstructor(
            @RequestBody Instructor instructor) {

        Instructor createdInstructor =
                instructorService.createInstructor(instructor);

        ApiResponse<Instructor> response =
                new ApiResponse<>(
                        true,
                        "Create instructor successfully",
                        createdInstructor
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>>
    updateInstructor(
            @PathVariable int id,
            @RequestBody Instructor updatedInstructor) {

        Instructor instructor =
                instructorService.updateInstructor(
                        id,
                        updatedInstructor
                );

        // NOT FOUND
        if (instructor == null) {

            ApiResponse<?> response =
                    new ApiResponse<>(
                            false,
                            "Instructor not found",
                            null
                    );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        // SUCCESS
        ApiResponse<Instructor> response =
                new ApiResponse<>(
                        true,
                        "Update instructor successfully",
                        instructor
                );

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>>
    deleteInstructor(@PathVariable int id) {

        Instructor instructor =
                instructorService.deleteInstructorById(id);

        // NOT FOUND
        if (instructor == null) {

            ApiResponse<?> response =
                    new ApiResponse<>(
                            false,
                            "Instructor not found",
                            null
                    );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        // SUCCESS
        ApiResponse<?> response =
                new ApiResponse<>(
                        true,
                        "Delete instructor successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }
}