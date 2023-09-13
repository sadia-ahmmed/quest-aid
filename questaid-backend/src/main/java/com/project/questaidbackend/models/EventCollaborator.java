package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventCollaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NonNull
    @ManyToOne(optional = false, targetEntity = Event.class)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @JsonIgnore
    private Event event;

    @ManyToOne(targetEntity = Club.class)
    @JoinColumn(name = "club_collaborator_id", referencedColumnName = "id")
    private Club clubCollaborator;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "org_collaborator_id", referencedColumnName = "id")
    @JsonIgnore
    private Organization orgCollaborator;

}
