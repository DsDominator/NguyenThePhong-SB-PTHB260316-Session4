package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
import com.example.coursemanagement.dto.request.CourseCreateRequest;
import com.example.coursemanagement.dto.request.CourseUpdateRequest;
import com.example.coursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createCourse(
            @RequestBody CourseCreateRequest req) {

        try {
            courseService.createCourse(req);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            true,
                            "Create course successfully",
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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseUpdateRequest req) {

        try {
            courseService.updateCourse(id, req);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Update course successfully",
                            null
                    )
            );

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