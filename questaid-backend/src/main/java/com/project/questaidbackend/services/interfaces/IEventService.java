package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Event;
import com.project.questaidbackend.models.Student;

import java.util.List;


public interface IEventService {
    Event createEvent(Event event, Long organizerId, String organizerType);
    Event getEventById(Long eventId);
    List<Event> viewEventsOfClub(Long clubId);
    List<Event> viewEventsOfOrg(Long orgId);
    Event verifyEvent(Long id);
    Event addParticipant(Long participantId, Long eventId);
    List<Student> getEventParticipants(Long eventId);
}
