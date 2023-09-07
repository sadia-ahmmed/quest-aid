package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
