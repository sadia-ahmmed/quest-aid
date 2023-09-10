package com.project.questaidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.questaidbackend.models.enums.PayingEntityType;
import com.project.questaidbackend.models.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NonNull
    @ManyToOne(optional = false, targetEntity = Club.class)
    @JoinColumn(name = "payed_to", referencedColumnName = "id")
    @JsonIgnore
    private Club payedTo;

//    solve the payed by issue
//    @NonNull
//    @ManyToOne(optional = false, targetEntity = Club.class)
//    @JoinColumn(name = "payed_to", referencedColumnName = "id")
//    private Club payedTo;


    @NonNull
    @Column(nullable = false)
    private double amount;


    @NonNull
    @Column
    private String reference;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayingEntityType payingEntity;

}
