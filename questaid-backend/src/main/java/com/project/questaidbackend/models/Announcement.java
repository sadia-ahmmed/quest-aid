package com.project.questaidbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NonNull
    @Column
    private String title;

    @NonNull
    @Column(length = 1000)
    private String content;

    @NonNull
    @Column
    private LocalDate datePublished;

    @NonNull
    @Column
    private String privacy;

    @ManyToOne(optional = false, targetEntity = Club.class)
    @JoinColumn(name = "announcer_id", referencedColumnName = "id")
    private Club announcer;

}
