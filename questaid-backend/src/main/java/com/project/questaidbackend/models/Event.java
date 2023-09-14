package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(targetEntity = Club.class)
    @JoinColumn(name = "club_organizer_id", referencedColumnName = "id")
    @JsonIgnore
    private Club clubOrganizer;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "org_organizer_id", referencedColumnName = "id")
    @JsonIgnore
    private Organization orgOrganizer;

    @NonNull
    @Column(nullable = false)
    private String eventName;

    @NonNull
    @Column(nullable = false)
    private String eventDescription;

    @NonNull
    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private boolean approved = false;

    @OneToMany(mappedBy = "event", targetEntity = EventCollaborator.class)
    private List<EventCollaborator> collaboratorList;

//  TODO:  * many to many reln with student -> event participants
    @ManyToMany
    @JoinTable(
            name = "participants",

            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonIgnore
    private List<Student> participants;
}
