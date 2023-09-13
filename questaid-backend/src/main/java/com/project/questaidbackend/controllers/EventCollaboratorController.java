package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.EventCollaborator;
import com.project.questaidbackend.services.interfaces.IEventCollaboratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/collaborator")
public class EventCollaboratorController {

    private IEventCollaboratorService eventCollaboratorService;

    /**
     * Add a collaborator to an event
     * @param eventId: the unique id of the event
     * @param collaboratorId: the unique id of the collaborator
     * @param collaboratorType: the type "club"/"org" in string
     * @return JSON
     */
    @PostMapping("/add/{eventId}/to/{collaboratorId}/{collaboratorType}")
    public ResponseEntity<EventCollaborator> addCollaborator(@PathVariable Long eventId, @PathVariable Long collaboratorId, @PathVariable String collaboratorType) {
        return new ResponseEntity<>(
                eventCollaboratorService.addCollaborator(new EventCollaborator(), eventId, collaboratorId, collaboratorType),
                HttpStatus.CREATED
        );
    }

    /**
     * Get all collaborators of an event
     * @param eventId: the unique ID of the event
     * @return JSON
     */
    @GetMapping("/view/{eventId}")
    public ResponseEntity<List<EventCollaborator>> getEventCollaborators(@PathVariable Long eventId) {
        return new ResponseEntity<>(
                eventCollaboratorService.getAllCollaborators(eventId),
                HttpStatus.OK
        );
    }

}
