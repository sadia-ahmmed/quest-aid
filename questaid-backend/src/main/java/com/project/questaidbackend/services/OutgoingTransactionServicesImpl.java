package com.project.questaidbackend.services;

import com.project.questaidbackend.models.OutgoingTransaction;
import com.project.questaidbackend.models.Treasury;
import com.project.questaidbackend.repository.OutgoingTransactionRepository;
import com.project.questaidbackend.services.interfaces.IOutgoingTransactionService;
import com.project.questaidbackend.services.interfaces.ITreasuryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OutgoingTransactionServicesImpl implements IOutgoingTransactionService {

    private OutgoingTransactionRepository outgoingTransactionRepository;

    private ITreasuryService treasuryService;

    @Override
    public OutgoingTransaction addOutgoingTransaction(OutgoingTransaction outgoingTransaction, Long treasuryId) {
        Treasury treasury = treasuryService.getTreasuryById(treasuryId);
        outgoingTransaction.setClubTreasury(treasury);
        return outgoingTransactionRepository.save(outgoingTransaction);
    }

    @Override
    public OutgoingTransaction viewOutgoingTransactionById(Long id) {
        return null;
    }

    @Override
    public List<OutgoingTransaction> viewAllOutgoingTransactionsByTypeAndTreasuryId(String type, Long treasuryId) {
        return outgoingTransactionRepository.findByClubTreasuryIdAndTransactionType(treasuryId, type);
    }
}
