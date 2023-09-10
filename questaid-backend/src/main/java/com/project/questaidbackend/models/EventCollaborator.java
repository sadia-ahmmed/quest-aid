package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @NonNull
    @ManyToOne(optional = false, targetEntity = Club.class)
    @JoinColumn(name = "collaborator_id", referencedColumnName = "id")
    @JsonIgnore
    private Club collaborator;

}
