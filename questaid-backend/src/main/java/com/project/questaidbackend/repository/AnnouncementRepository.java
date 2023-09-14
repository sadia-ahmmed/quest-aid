package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.Announcement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {
    List<Announcement> findByAnnouncerId(Long id);
    List<Announcement> findByPrivacyAndAnnouncerId(String privacy, Long announcerId);
}
