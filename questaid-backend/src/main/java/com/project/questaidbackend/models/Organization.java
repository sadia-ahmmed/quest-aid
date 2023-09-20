package com.project.questaidbackend.models;

import com.project.questaidbackend.models.aggregate.SocialLinks;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(nullable = false)
    private String phone;

    @Column
    private String avatarPath;


    @OneToMany(mappedBy = "organizationEntity", cascade = CascadeType.ALL, targetEntity = SocialLinks.class)
    private List<SocialLinks> socialLinks;

    @OneToMany(mappedBy = "orgOrganizer", cascade = CascadeType.ALL, targetEntity = Event.class)
    private List<Event> eventList;

    @OneToMany(mappedBy = "orgCollaborator", cascade = CascadeType.ALL, targetEntity = EventCollaborator.class)
    private List<EventCollaborator> collaboratedEventsList;

}
