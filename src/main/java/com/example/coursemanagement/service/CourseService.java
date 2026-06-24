package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.request.CourseCreateRequest;
import com.example.coursemanagement.dto.request.CourseUpdateRequest;
import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.model.Instructor;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public CourseService(
            CourseRepository courseRepository,
            InstructorRepository instructorRepository) {

        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public Course createCourse(CourseCreateRequest req) {

        Instructor instructor = instructorRepository
                .findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = new Course();
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, CourseUpdateRequest req) {

        Course course = courseRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Instructor instructor = instructorRepository
                .findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }
}