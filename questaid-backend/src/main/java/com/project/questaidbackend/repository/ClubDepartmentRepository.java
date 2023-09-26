package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.ClubDepartment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClubDepartmentRepository extends CrudRepository<ClubDepartment, Long> {
    List<ClubDepartment> findByClubId(Long id);
}
