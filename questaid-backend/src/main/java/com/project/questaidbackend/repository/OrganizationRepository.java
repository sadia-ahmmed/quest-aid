package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
    Optional<Organization> findByEmail(String email);
}
