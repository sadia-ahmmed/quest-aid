package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.ClubMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClubMemberRepository extends CrudRepository<ClubMember, Long> {
    Optional<ClubMember> findByStudentId(Long id);
}
