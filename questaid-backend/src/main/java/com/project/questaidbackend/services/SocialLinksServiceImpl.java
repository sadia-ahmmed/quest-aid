package com.project.questaidbackend.services;

import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.models.aggregate.SocialLinks;
import com.project.questaidbackend.repository.SocialLinksRepository;
import com.project.questaidbackend.services.interfaces.IClubService;
import com.project.questaidbackend.services.interfaces.ISocialLinksService;
import com.project.questaidbackend.services.interfaces.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SocialLinksServiceImpl implements ISocialLinksService {

    private SocialLinksRepository socialLinksRepository;

    private IStudentService studentService;
    private IClubService clubService;

    @Override
    public SocialLinks addLinkForEntity(SocialLinks socialLinks, Long entityId, String entityType) {
        switch (entityType) {
            case "student" -> {
                Student student = studentService.getStudentById(entityId);
                socialLinks.setStudentEntity(student);
                return socialLinksRepository.save(socialLinks);
            }
            case "club" -> {
                Club club = clubService.getClubById(entityId);
                socialLinks.setClubEntity(club);
                return socialLinksRepository.save(socialLinks);
            }
            case "org" -> {
//                Club club = clubService.getClub(entityId);
//                socialLinks.setClubEntity(club);
//                return socialLinksRepository.save(socialLinks);
                return null;
            }
            default -> {
                throw new RuntimeException("Not a valid class");
            }
        }
    }

    @Override
    public SocialLinks updateLinkForEntity(SocialLinks socialLinks, Long entityId, String entityType) {
        // TODO: complete this function
        return null;
    }

    @Override
    public void removeLinkForEntity(Long entityId, String entityType) {
        // TODO: complete this function
    }

    @Override
    public List<SocialLinks> getSocialLinks(Long entityId, String entityType) {
        switch (entityType) {
            case "student" -> {
                return socialLinksRepository.findByStudentEntityId(entityId);
            }
            case "club" -> {
                return socialLinksRepository.findByClubEntityId(entityId);
            }
            case "org" -> {
                return null;
            }
            default -> {
                throw new RuntimeException("Not a valid class");
            }
        }
    }

}
