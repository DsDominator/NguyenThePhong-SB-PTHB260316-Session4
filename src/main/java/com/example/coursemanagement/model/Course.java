package com.example.coursemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CourseStatus status;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<StudentEnrollment> enrollments = new ArrayList<>();

    public Course() {
    }

    public Course(Long id, String title, CourseStatus status, Instructor instructor) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructor = instructor;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<StudentEnrollment> getEnrollments() {
        return enrollments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setEnrollments(List<StudentEnrollment> enrollments) {
        this.enrollments = enrollments;
    }
}