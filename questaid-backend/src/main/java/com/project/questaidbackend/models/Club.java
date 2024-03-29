package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column
    private String avatarPath;

    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(unique = true, nullable = false)
    private String phone;


    @ManyToOne(optional = false, targetEntity = Admin.class)
    @JoinColumn(name = "assigned_admin", referencedColumnName = "id")
    @JsonIgnoreProperties({"password", "assignedAdmin"})
    private Admin assignedAdmin;

    // * a one-to-many mapping to club members class
    @JsonIgnore
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, targetEntity = ClubMember.class)
    private List<ClubMember> clubMembers;

    // * a one-to-many mapping to club departments class
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, targetEntity = ClubDepartment.class)
    @JsonIgnore
    private List<ClubDepartment> clubDepartments;

    // * a one-to-many mapping to task class
    @JsonIgnore
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, targetEntity = Task.class)
    private List<Task> memberTasks;

    // * a one-to-one mapping to treasury class
    @OneToOne(mappedBy = "club", cascade = CascadeType.ALL, targetEntity = Treasury.class)
    @JsonIgnore
    private Treasury treasury;

    // * a one-to-many mapping to event class
    @OneToMany(mappedBy = "clubOrganizer", cascade = CascadeType.PERSIST, targetEntity = Event.class)
    @JsonIgnore
    private List<Event> events;

    @OneToMany(mappedBy = "clubEntity", cascade = CascadeType.ALL, targetEntity = SocialLinks.class)
    private List<SocialLinks> socialLinks;

    @OneToMany(mappedBy = "clubCollaborator", cascade = CascadeType.PERSIST, targetEntity = EventCollaborator.class)
    @JsonIgnore
    private List<EventCollaborator> collaboratedEventsList;

    @OneToMany(mappedBy = "announcer", cascade = CascadeType.ALL, targetEntity = Announcement.class)
    @JsonIgnore
    private List<Announcement> announcements;


    @OneToMany(mappedBy = "requester", cascade = CascadeType.ALL, targetEntity = FundRequest.class)
    @JsonIgnore
    private List<FundRequest> fundRequests;

}
