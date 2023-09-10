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
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(unique = true, nullable = false)
    private String phone;

    @Column
    private String avatar;

    @NonNull
    @Column(nullable = false)
    private boolean verified = false;

    // * a one-to-many mapping to club members class
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, targetEntity = ClubMember.class)
    @JsonIgnore
    private List<ClubMember> clubsJoined;
}
