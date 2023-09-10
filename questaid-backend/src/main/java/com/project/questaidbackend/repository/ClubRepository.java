package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.Club;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClubRepository extends CrudRepository<Club, Long> {
    Optional<Club> findByEmail(String email);
    Optional<Club> findByClubName(String name);
}
