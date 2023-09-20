package com.project.questaidbackend.models;

import com.project.questaidbackend.models.enums.PayingEntityType;
import com.project.questaidbackend.models.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IncomingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NonNull
    @Column
    private String payedBy;

    @NonNull
    @Column(nullable = false)
    private double amount = 0.0d;

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

    @NonNull
    @Column
    private LocalDate transactionDate;


    @NonNull
    @ManyToOne(targetEntity = Treasury.class)
    @JoinColumn(name = "payed_to_treasury", referencedColumnName = "id")
    private Treasury clubTreasury;


}
