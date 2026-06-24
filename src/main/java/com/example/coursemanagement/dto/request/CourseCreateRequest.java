package com.example.coursemanagement.dto.request;

import com.example.coursemanagement.model.CourseStatus;

public class CourseCreateRequest {

    private String title;
    private CourseStatus status;
    private Long instructorId;

    public String getTitle() {
        return title;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }
}