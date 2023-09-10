package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NonNull
    @ManyToOne(optional = false, targetEntity = ClubMember.class)
    @JsonIgnore
    @JoinColumn(name = "assigned_to_id", referencedColumnName = "id")
    private ClubMember clubMember;

    @NonNull
    @ManyToOne(optional = false, targetEntity = Club.class)
    @JsonIgnore
    @JoinColumn(name = "assigned_by_id", referencedColumnName = "id")
    private Club club;


    @NonNull
    @Column(nullable = false)
    private String title;

    @NonNull
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean status = false;

}
