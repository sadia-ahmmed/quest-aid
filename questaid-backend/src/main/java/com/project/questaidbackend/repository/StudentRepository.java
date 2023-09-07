package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE student s SET s.verified = :status WHERE s.id = :id")
    void updateStudent(@Param(value = "status") Boolean status, @Param(value = "id") Long id);
}
