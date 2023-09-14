package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.aggregate.SocialLinks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocialLinksRepository extends CrudRepository<SocialLinks, Long> {
    List<SocialLinks> findByStudentEntityId(Long id);
    List<SocialLinks> findByClubEntityId(Long id);
    List<SocialLinks> findByOrganizationEntityId(Long id);
}
