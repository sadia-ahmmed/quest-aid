package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.OutgoingTransaction;

import java.util.List;

public interface IOutgoingTransactionService {
    OutgoingTransaction addOutgoingTransaction(OutgoingTransaction outgoingTransaction, Long treasuryId);
    OutgoingTransaction viewOutgoingTransactionById(Long id);
    List<OutgoingTransaction> viewAllOutgoingTransactionsByTypeAndTreasuryId(String type, Long treasuryId);
}
