package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
