package com.project.questaidbackend.services;

import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.Event;
import com.project.questaidbackend.models.EventCollaborator;
import com.project.questaidbackend.models.Organization;
import com.project.questaidbackend.repository.EventCollaboratorRepository;
import com.project.questaidbackend.services.interfaces.IClubService;
import com.project.questaidbackend.services.interfaces.IEventCollaboratorService;
import com.project.questaidbackend.services.interfaces.IEventService;
import com.project.questaidbackend.services.interfaces.IOrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventCollaboratorServiceImpl implements IEventCollaboratorService {

    private EventCollaboratorRepository eventCollaboratorRepository;

    private IEventService eventService;
    private IClubService clubService;
    private IOrganizationService organizationService;

    @Override
    public EventCollaborator addCollaborator(EventCollaborator eventCollaborator, Long eventId, Long collaboratorId, String collaboratorType) {
        Event event = eventService.getEventById(eventId);
        eventCollaborator.setEvent(event);

        if (collaboratorType.equals("club")) {
            Club club = clubService.getClubById(collaboratorId);
            eventCollaborator.setClubCollaborator(club);
        } else {
            Organization organization = organizationService.getOrganizationById(collaboratorId);
            eventCollaborator.setOrgCollaborator(organization);
        }

        return eventCollaboratorRepository.save(eventCollaborator);
    }

    @Override
    public List<EventCollaborator> getAllCollaborators(Long eventId) {
        return eventCollaboratorRepository.findByEventId(eventId);
    }
}
