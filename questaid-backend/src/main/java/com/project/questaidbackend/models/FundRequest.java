package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FundRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NonNull
    @Column
    private double requestedFund;

    @ManyToOne(optional = false, targetEntity = Club.class)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private Club requester;

    @ManyToOne(optional = false, targetEntity = Admin.class)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private Admin requestedTo;

    @Column
    private boolean approved = false;
}
