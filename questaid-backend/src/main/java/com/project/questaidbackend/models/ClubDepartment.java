package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "club_department")
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClubDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(optional = false, targetEntity = Club.class)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @JsonIgnore
    private Club club;

    @NonNull
    @Column
    private String departmentName;

    // * list of all members in a department
    @OneToMany(mappedBy = "clubDepartment", targetEntity = ClubMember.class)
    @JsonIgnore
    private List<ClubMember> clubMemberList;

}
