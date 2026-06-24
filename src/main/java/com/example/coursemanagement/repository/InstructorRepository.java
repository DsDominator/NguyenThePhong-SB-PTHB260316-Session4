package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {

    private final List<Instructor> instructors = new ArrayList<>();

    public InstructorRepository() {

        instructors.add(
                new Instructor(1L,
                        "John Smith",
                        "john@gmail.com")
        );

        instructors.add(
                new Instructor(2L,
                        "Alice Brown",
                        "alice@gmail.com")
        );
    }

    // FIND ALL
    public List<Instructor> findAll() {
        return instructors;
    }

    // FIND BY ID
    public Instructor findById(int id) {

        for (Instructor instructor : instructors) {

            if (instructor.getId() == id) {
                return instructor;
            }
        }

        return null;
    }

    // CREATE
    public Instructor create(Instructor instructor) {

        instructors.add(instructor);

        return instructor;
    }

    // UPDATE
    public Instructor update(int id, Instructor updatedInstructor) {

        Instructor existingInstructor = findById(id);

        if (existingInstructor == null) {
            return null;
        }

        existingInstructor.setName(updatedInstructor.getName());
        existingInstructor.setEmail(updatedInstructor.getEmail());

        return existingInstructor;
    }

    // DELETE
    public Instructor deleteById(int id) {

        Instructor instructor = findById(id);

        if (instructor == null) {
            return null;
        }

        instructors.remove(instructor);

        return instructor;
    }
}