package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findByClubOrganizerId(Long clubId);
    List<Event> findByOrgOrganizerId(Long orgId);
}
