package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.questaidbackend.models.aggregate.SocialLinks;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "student")
@Table(name = "student")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column
    private String universityId;

    @NonNull
    @Column(unique = true, nullable = false)
    private String phone;

    @Column
    private String avatarPath;

    @NonNull
    @Column(nullable = false)
    private boolean verified = true;

    // * a one-to-many mapping to club members class
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, targetEntity = ClubMember.class)
//    @JsonIgnore
    private List<ClubMember> clubsJoined;

    // * a one-to-many mapping to social links
    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL, targetEntity = SocialLinks.class)
    private List<SocialLinks> socialLinks;

    @ManyToMany(mappedBy = "participants", targetEntity = Event.class, cascade = CascadeType.ALL)
    private List<Event> eventsParticipating;

    @ManyToOne(optional = false, targetEntity = Admin.class)
    @JoinColumn(referencedColumnName = "id")
    private Admin assignedUnderAdmin;

}
