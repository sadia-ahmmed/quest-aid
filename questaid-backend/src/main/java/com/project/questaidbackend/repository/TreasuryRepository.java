package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.Treasury;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TreasuryRepository extends CrudRepository<Treasury, Long> {
    Optional<Treasury> findByClubId(Long id);
}
