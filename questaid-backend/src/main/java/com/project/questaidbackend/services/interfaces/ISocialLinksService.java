package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.aggregate.SocialLinks;

import java.util.List;

public interface ISocialLinksService {
    SocialLinks addLinkForEntity(SocialLinks socialLinks, Long entityId, String entityType);

    SocialLinks updateLinkForEntity(SocialLinks socialLinks, Long entityId, String entityType);

    void removeLinkForEntity(Long entityId, String entityType);

    List<SocialLinks> getSocialLinks(Long entityId, String entityType);
}
