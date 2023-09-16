package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Event;
import com.project.questaidbackend.models.EventCollaborator;

import java.util.List;

public interface IEventCollaboratorService {
    EventCollaborator addCollaborator(EventCollaborator eventCollaborator, Long eventId, Long collaboratorId, String collaboratorType);
    List<EventCollaborator> getAllCollaborators(Long eventId);
}
