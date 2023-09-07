package com.project.questaidbackend.models;

import jakarta.persistence.*;
import lombok.*;

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
}
