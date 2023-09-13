package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.questaidbackend.models.aggregate.SocialLinks;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "club")
@Table(name = "club")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NonNull
    @Column(name = "club_name", unique = true, nullable = false)
    private String clubName;

    @NonNull
    @Column(name = "club_logo_path")
    private String clubLogoPath;

    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(unique = true, nullable = false)
    private String phone;

    // * a one-to-many mapping to club members class
//    @JsonIgnore
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, targetEntity = ClubMember.class)
    private List<ClubMember> clubMembers;

    // * a one-to-many mapping to club departments class
//    @JsonIgnore
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, targetEntity = ClubDepartment.class)
    private List<ClubDepartment> clubDepartments;

    // * a one-to-many mapping to task class
    @JsonIgnore
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, targetEntity = Task.class)
    private List<Task> memberTasks;

    // * a one-to-many mapping to transaction class
//    @JsonIgnore
    @OneToMany(mappedBy = "payedTo", cascade = CascadeType.ALL, targetEntity = Transaction.class)
    private List<Transaction> transactions;

    // * a one-to-many mapping to event class
    @OneToMany(mappedBy = "clubOrganizer", cascade = CascadeType.ALL, targetEntity = Event.class)
    private List<Event> events;

    @OneToMany(mappedBy = "clubEntity", cascade = CascadeType.ALL, targetEntity = SocialLinks.class)
    private List<SocialLinks> socialLinks;

    @OneToMany(mappedBy = "clubCollaborator", cascade = CascadeType.ALL, targetEntity = EventCollaborator.class)
    @JsonIgnore
    private List<EventCollaborator> collaboratedEventsList;

}
