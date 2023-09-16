package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.Event;
import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.services.interfaces.IEventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {

    private IEventService eventService;

    @PostMapping("/create/{organizerId}/{organizerType}")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, @PathVariable Long organizerId, @PathVariable String organizerType) {
        return new ResponseEntity<>(eventService.createEvent(event, organizerId, organizerType), HttpStatus.CREATED);
    }

    @GetMapping("/view/{eventId}")
    public ResponseEntity<Event> viewEvent(@PathVariable Long eventId) {
        return new ResponseEntity<>(eventService.getEventById(eventId), HttpStatus.OK);
    }

    @GetMapping("/view/{clubId}/club")
    public ResponseEntity<List<Event>> viewEventsOfClub(@PathVariable Long clubId) {
        return new ResponseEntity<>(eventService.viewEventsOfClub(clubId), HttpStatus.OK);
    }

    @GetMapping("/view/{orgId}/organization")
    public ResponseEntity<List<Event>> viewEventsOfOrg(@PathVariable Long orgId) {
        return new ResponseEntity<>(eventService.viewEventsOfOrg(orgId), HttpStatus.OK);
    }

    @GetMapping("/view/{eventId}/participants")
    public ResponseEntity<List<Student>> viewParticipants(@PathVariable Long eventId) {
        return new ResponseEntity<>(eventService.getEventParticipants(eventId), HttpStatus.OK);
    }

    @PutMapping("/verify/{eventId}")
    public ResponseEntity<Event> verifyEvent(@PathVariable Long eventId) {
        return new ResponseEntity<>(eventService.verifyEvent(eventId), HttpStatus.OK);
    }

    @PutMapping("/add/{participantId}/to/{eventId}")
    public ResponseEntity<Event> addParticipant(@PathVariable Long participantId, @PathVariable Long eventId) {
        return new ResponseEntity<>(eventService.addParticipant(participantId, eventId), HttpStatus.OK);
    }

}
