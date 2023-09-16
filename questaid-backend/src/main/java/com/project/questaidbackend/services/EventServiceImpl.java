package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.Event;
import com.project.questaidbackend.models.Organization;
import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.repository.EventRepository;
import com.project.questaidbackend.services.interfaces.IClubService;
import com.project.questaidbackend.services.interfaces.IEventService;
import com.project.questaidbackend.services.interfaces.IOrganizationService;
import com.project.questaidbackend.services.interfaces.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImpl implements IEventService {

    private EventRepository eventRepository;

    private IClubService clubService;
    private IOrganizationService organizationService;
    private IStudentService studentService;

    @Override
    public Event createEvent(Event event, Long organizerId, String organizerType) {
        switch (organizerType) {
            case "club" -> {
                Club club = clubService.getClubById(organizerId);
                event.setClubOrganizer(club);
                return eventRepository.save(event);
            }
            case "org" -> {
                Organization organization = organizationService.getOrganizationById(organizerId);
                event.setOrgOrganizer(organization);
                return eventRepository.save(event);
            }
            default -> {
                throw new RuntimeException("Invalid choice");
            }
        }
    }

    @Override
    public Event getEventById(Long eventId) {
        return unwrapEvent(eventRepository.findById(eventId), eventId);
    }

    @Override
    public List<Event> viewEventsOfClub(Long clubId) {
        return eventRepository.findByClubOrganizerId(clubId);
    }

    @Override
    public List<Event> viewEventsOfOrg(Long orgId) {
        return eventRepository.findByOrgOrganizerId(orgId);
    }

    @Override
    public Event verifyEvent(Long id) {
        Event event = unwrapEvent(eventRepository.findById(id), id);
        event.setApproved(true);
        return eventRepository.save(event);
    }

    @Override
    public Event addParticipant(Long participantId, Long eventId) {
        Student student = studentService.getStudentById(participantId);
        Event event = unwrapEvent(eventRepository.findById(eventId), eventId);

        event.getParticipants().add(student);

        return eventRepository.save(event);
    }

    @Override
    public List<Student> getEventParticipants(Long eventId) {
        return unwrapEvent(eventRepository.findById(eventId), eventId).getParticipants();
    }

    public static Event unwrapEvent(Optional<Event> event, Long id) {
        if (event.isPresent()) return event.get();
        else throw new EntityNotFoundException(id, Event.class);
    }
}
