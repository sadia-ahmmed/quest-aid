package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.OutgoingTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OutgoingTransactionRepository extends CrudRepository<OutgoingTransaction, Long> {
    List<OutgoingTransaction> findByClubTreasuryId(Long id);
    List<OutgoingTransaction> findByTransactionType(String type);
    List<OutgoingTransaction> findByClubTreasuryIdAndTransactionType(Long id, String type);
}
