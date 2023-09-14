package com.project.questaidbackend.models.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.Organization;
import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.models.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocialLinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


    @JsonIgnore
    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "student_entity_id", referencedColumnName = "id")
    private Student studentEntity;


    @JsonIgnore
    @ManyToOne(targetEntity = Club.class)
    @JoinColumn(name = "club_entity_id", referencedColumnName = "id")
    private Club clubEntity;


    @JsonIgnore
    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "org_entity_id", referencedColumnName = "id")
    private Organization organizationEntity;


    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @NonNull
    @Column
    private String url;
}
