package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.Announcement;
import com.project.questaidbackend.services.interfaces.IAnnouncementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/announcement")
public class AnnouncementController {

    private IAnnouncementService announcementService;

    @PostMapping("/add/by/{clubId}")
    public ResponseEntity<Announcement> addAnnouncement(@Valid @RequestBody Announcement announcement, @PathVariable Long clubId) {
        return new ResponseEntity<>(announcementService.addAnnouncement(announcement, clubId), HttpStatus.CREATED);
    }

    @GetMapping("/view/{id}/announcement")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable Long id) {
        return new ResponseEntity<>(announcementService.getAnnouncementById(id), HttpStatus.OK);
    }

    @GetMapping("/view/all/{id}/club")
    public ResponseEntity<List<Announcement>> getAnnouncementByAnnouncerId(@PathVariable Long id) {
        return new ResponseEntity<>(announcementService.getAnnouncementByAnnouncerId(id), HttpStatus.OK);
    }

    @GetMapping("/view/all/{privacy}/{clubId}/club")
    public ResponseEntity<List<Announcement>> getAnnouncementsByClub(@PathVariable String privacy, @PathVariable Long clubId) {
        return new ResponseEntity<>(announcementService.getAnnouncementsByClub(privacy, clubId), HttpStatus.OK);
    }

}
