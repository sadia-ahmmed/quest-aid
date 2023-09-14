package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Announcement;

import java.util.List;

public interface IAnnouncementService {
    Announcement addAnnouncement(Announcement announcement, Long clubId);
    Announcement getAnnouncementById(Long id);
    List<Announcement> getAnnouncementByAnnouncerId(Long id);
    List<Announcement> getAnnouncementsByClub(String privacy, Long clubId);
}
