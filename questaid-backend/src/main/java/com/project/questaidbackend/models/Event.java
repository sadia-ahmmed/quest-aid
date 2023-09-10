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

    @NonNull
    @ManyToOne(optional = false, targetEntity = Club.class)
    @JoinColumn(name = "organizer_id", referencedColumnName = "id")
    @JsonIgnore
    private Club organizer;

    @NonNull
    @Column(nullable = false)
    private String eventName;

    @NonNull
    @Column(nullable = false)
    private String eventDescription;

    @NonNull
    @Column(nullable = false)
    private String eventType;


    @OneToMany(mappedBy = "event", targetEntity = EventCollaborator.class)
    @JsonIgnore
    private List<EventCollaborator> collaboratorList;

}
