package com.project.questaidbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
    private Club club;

    @OneToMany(mappedBy = "clubTreasury", targetEntity = IncomingTransaction.class)
    private List<IncomingTransaction> incomingTransactions;

    @OneToMany(mappedBy = "clubTreasury", targetEntity = OutgoingTransaction.class)
    private List<OutgoingTransaction> outgoingTransactions;

}
