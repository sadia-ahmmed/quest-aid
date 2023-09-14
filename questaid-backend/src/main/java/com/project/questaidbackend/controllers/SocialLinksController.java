package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.aggregate.SocialLinks;
import com.project.questaidbackend.services.interfaces.ISocialLinksService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/links")
public class SocialLinksController {

    private ISocialLinksService socialLinksService;

    /**
     * Add a social link to an entity
     * @param socialLink: the body of the social link
     * @param entityId: the unique id of the entity
     * @param entityType: the type of the entity
     * @return a JSON
     */
    @PostMapping("/add/{entityId}/{entityType}")
    public ResponseEntity<SocialLinks> addSocialLink(@Valid @RequestBody SocialLinks socialLink, @PathVariable Long entityId, @PathVariable String entityType) {
        return new ResponseEntity<>(socialLinksService.addLinkForEntity(socialLink, entityId, entityType), HttpStatus.CREATED);
    }

    /**
     * Get all social links for an entity
     * @param entityId
     * @param entityType
     * @return
     */
    @GetMapping("/get-all/{entityId}/{entityType}")
    public ResponseEntity<List<SocialLinks>> getAllSocialLinks(@PathVariable Long entityId, @PathVariable String entityType) {
        return new ResponseEntity<>(socialLinksService.getSocialLinks(entityId, entityType), HttpStatus.OK);
    }

}
