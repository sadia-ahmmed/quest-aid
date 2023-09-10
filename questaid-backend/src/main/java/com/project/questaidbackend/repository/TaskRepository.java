package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findTaskByClubMemberId(Long id);
    List<Task> findTaskByClubId(Long id);
}
