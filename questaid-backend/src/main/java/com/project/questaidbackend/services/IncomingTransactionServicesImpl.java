package com.project.questaidbackend.services;

import com.project.questaidbackend.models.IncomingTransaction;
import com.project.questaidbackend.models.Treasury;
import com.project.questaidbackend.repository.IncomingTransactionRepository;
import com.project.questaidbackend.services.interfaces.IIncomingTransactionService;
import com.project.questaidbackend.services.interfaces.ITreasuryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IncomingTransactionServicesImpl implements IIncomingTransactionService {

    private IncomingTransactionRepository incomingTransactionRepository;

    private ITreasuryService treasuryService;

    @Override
    public IncomingTransaction addIncomingTransaction(IncomingTransaction incomingTransaction, Long treasuryId) {
        Treasury treasury = treasuryService.getTreasuryById(treasuryId);
        incomingTransaction.setClubTreasury(treasury);
        return incomingTransactionRepository.save(incomingTransaction);
    }

    @Override
    public IncomingTransaction viewTransactionById(Long id) {
        return null;
    }

    @Override
    public List<IncomingTransaction> viewAllIncomingTransactionsByTypeAndTreasuryId(String type, Long treasuryId) {
        return incomingTransactionRepository.findByClubTreasuryIdAndTransactionType(treasuryId, type);
    }
}
