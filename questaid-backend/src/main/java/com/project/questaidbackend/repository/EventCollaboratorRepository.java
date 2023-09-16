package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.EventCollaborator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventCollaboratorRepository extends CrudRepository<EventCollaborator, Long> {
    List<EventCollaborator> findByEventId(Long eventId);
}
