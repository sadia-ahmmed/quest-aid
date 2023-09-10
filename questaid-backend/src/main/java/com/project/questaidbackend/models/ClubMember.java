package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.questaidbackend.models.enums.ClubMemberRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.List;

@Entity(name = "club_member")
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClubMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NonNull
    @ManyToOne(optional = false, targetEntity = Student.class)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    @JsonIgnore
    private Student student;

    @NonNull
    @ManyToOne(optional = false, targetEntity = Club.class)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @JsonIgnore
    private Club club;

    @NonNull
    @ManyToOne(optional = false, targetEntity = ClubDepartment.class)
    @JsonIgnore
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = true)
    private ClubDepartment clubDepartment;


    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClubMemberRoles clubMemberRoles;


    // one to many mapping to task class
    @OneToMany(mappedBy = "clubMember", cascade = CascadeType.ALL, targetEntity = Task.class)
//    @JsonIgnore
    private List<Task> tasks;

}
