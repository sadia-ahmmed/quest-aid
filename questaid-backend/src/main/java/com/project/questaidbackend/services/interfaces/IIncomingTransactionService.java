package com.project.questaidbackend.services.interfaces;


import com.project.questaidbackend.models.IncomingTransaction;

import java.util.List;

public interface IIncomingTransactionService {
    /**
     * Add a transaction to the treasury of a club
     * @param incomingTransaction the java object of the incoming transaction
     * @param treasuryId the unique id of the treasury owned by the club
     * @return IncomingTransaction
     */
    IncomingTransaction addIncomingTransaction(IncomingTransaction incomingTransaction, Long treasuryId);

    /**
     * View a single transaction by their unique id
     * @param id the unique id of the incoming transaction
     * @return IncomingTransaction object returned
     */
    IncomingTransaction viewTransactionById(Long id);

    /**
     * View all the incoming transactions by their type and the unique treasury id
     * @param type The type of transaction = RECRUITMENT/EVENT
     * @param treasuryId The treasury id owned by a club
     * @return List of all incoming transactions
     */
    List<IncomingTransaction> viewAllIncomingTransactionsByTypeAndTreasuryId(String type, Long treasuryId);
}
