package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.model.CourseStatus;
import com.example.coursemanagement.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        Instructor instructor1 = new Instructor();
        instructor1.setId(1L);

        courses.add(
                new Course(
                        1L,
                        "Java Spring Boot",
                        CourseStatus.OPEN,
                        instructor1
                )
        );

        Instructor instructor2 = new Instructor();
        instructor1.setId(2L);
        new Course(
                2L,
                "Web Development",
                CourseStatus.CLOSED,
                instructor2
        );
    }

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(long id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return Optional.of(course);
            }
        }

        return Optional.empty();
    }

    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    public Course update(Long id, Course updatedCourse) {

        Course existingCourse = findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        existingCourse.setTitle(
                updatedCourse.getTitle()
        );

        existingCourse.setStatus(
                updatedCourse.getStatus()
        );

        existingCourse.setInstructor(
                updatedCourse.getInstructor()
        );

        return existingCourse;
    }

    public Course deleteById(long id) {
        Course existingCourse = findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        courses.remove(existingCourse);

        return existingCourse;
    }
}