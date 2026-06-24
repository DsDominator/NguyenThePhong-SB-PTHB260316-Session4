package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
import com.example.coursemanagement.model.Course;
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

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllCourses() {
        ApiResponse<?> response = new ApiResponse<>(
                true,
                "Get all courses successfully",
                courseService.getAllCourses()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getCourseById(
            @PathVariable long id) {

        try {
            Course course = courseService.findCourseById(id);

            ApiResponse<?> response = new ApiResponse<>(
                    true,
                    "Get course successfully",
                    course
            );

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            ApiResponse<?> response = new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createCourse(
            @RequestBody Course course) {

        Course createdCourse = courseService.createCourse(course);

        ApiResponse<?> response = new ApiResponse<>(
                true,
                "Create course successfully",
                createdCourse
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateCourse(
            @PathVariable long id,
            @RequestBody Course updatedCourse) {

        try {
            Course course = courseService.updateCourse(id, updatedCourse);

            ApiResponse<?> response = new ApiResponse<>(
                    true,
                    "Update course successfully",
                    course
            );

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            ApiResponse<?> response = new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteCourse(
            @PathVariable long id) {

        try {
            courseService.deleteCourseById(id);

            ApiResponse<?> response = new ApiResponse<>(
                    true,
                    "Delete course successfully",
                    null
            );

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            ApiResponse<?> response = new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    }
}