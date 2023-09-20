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
public class OutgoingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

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
    private PayingEntityType payingEntity = PayingEntityType.CLUB;

    @NonNull
    @Column
    private LocalDate transactionDate;

    @NonNull
    @ManyToOne(targetEntity = Treasury.class)
    @JoinColumn(name = "payed_by_treasury", referencedColumnName = "id")
    private Treasury clubTreasury;

}
