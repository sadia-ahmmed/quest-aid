package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Announcement;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.repository.AnnouncementRepository;
import com.project.questaidbackend.services.interfaces.IAnnouncementService;
import com.project.questaidbackend.services.interfaces.IClubService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnnouncementServicesImpl implements IAnnouncementService {

    private AnnouncementRepository announcementRepository;

    private IClubService clubService;

    @Override
    public Announcement addAnnouncement(Announcement announcement, Long clubId) {
        Club club = clubService.getClubById(clubId);
        announcement.setAnnouncer(club);
        return announcementRepository.save(announcement);
    }

    @Override
    public Announcement getAnnouncementById(Long id) {
        return unwrapAnnouncement(announcementRepository.findById(id), id);
    }

    @Override
    public List<Announcement> getAnnouncementByAnnouncerId(Long id) {
        return announcementRepository.findByAnnouncerId(id);
    }

    @Override
    public List<Announcement> getAnnouncementsByClub(String privacy, Long clubId) {
        return announcementRepository.findByPrivacyAndAnnouncerId(privacy, clubId);
    }

    private static Announcement unwrapAnnouncement(Optional<Announcement> announcement, Long id) {
        if(announcement.isPresent()) return announcement.get();
        else throw new EntityNotFoundException(id, Announcement.class);
    }

}
