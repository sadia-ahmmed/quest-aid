package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Treasury {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne(optional = false, targetEntity = Club.class)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @JsonIgnore
    private Club club;

    @NonNull
    @Column
    private double assignedBudget = 0.0d;

    @OneToMany(mappedBy = "clubTreasury", targetEntity = IncomingTransaction.class)
    @JsonIgnore
    private List<IncomingTransaction> incomingTransactions;

    @OneToMany(mappedBy = "clubTreasury", targetEntity = OutgoingTransaction.class)
    @JsonIgnore
    private List<OutgoingTransaction> outgoingTransactions;

}
