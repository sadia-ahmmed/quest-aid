package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.IncomingTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncomingTransactionRepository extends CrudRepository<IncomingTransaction, Long> {
    List<IncomingTransaction> findByClubTreasuryId(Long id);
    List<IncomingTransaction> findByTransactionType(String type);
    List<IncomingTransaction> findByClubTreasuryIdAndTransactionType(Long id, String type);
}
