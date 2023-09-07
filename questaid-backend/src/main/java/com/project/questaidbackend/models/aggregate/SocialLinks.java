package com.project.questaidbackend.models.aggregate;

import com.project.questaidbackend.helpers.enums.SocialType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@RequiredArgsConstructor
@Getter
@Setter
public class SocialLinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    private Object entity;

    @NonNull
    @Column(name = "social_type")
    private SocialType socialType;

    @NonNull
    @Column
    private String url;
}
