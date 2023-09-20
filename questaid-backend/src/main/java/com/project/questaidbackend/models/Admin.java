package com.project.questaidbackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "admins")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String avatarPath;

    @NotBlank(message = "Password cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column
    private double currentBudget = 0.0d;

    @OneToMany(mappedBy = "assignedAdmin", cascade = CascadeType.ALL, targetEntity = Club.class)
    private List<Club> assignedAdmin;
}
