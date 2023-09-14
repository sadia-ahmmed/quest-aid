package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.IncomingTransaction;
import com.project.questaidbackend.models.OutgoingTransaction;
import com.project.questaidbackend.models.Treasury;
import com.project.questaidbackend.services.interfaces.IIncomingTransactionService;
import com.project.questaidbackend.services.interfaces.IOutgoingTransactionService;
import com.project.questaidbackend.services.interfaces.ITreasuryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/treasury")
public class TreasuryController {

    private ITreasuryService treasuryService;
    private IIncomingTransactionService incomingTransactionService;
    private IOutgoingTransactionService outgoingTransactionService;

    @PostMapping("/add/{treasuryId}/incoming")
    public ResponseEntity<IncomingTransaction> addIncomingTransaction(@Valid @RequestBody IncomingTransaction incomingTransaction, @PathVariable Long treasuryId) {
        return new ResponseEntity<>(
                incomingTransactionService.addIncomingTransaction(incomingTransaction, treasuryId),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/add/{treasuryId}/outgoing")
    public ResponseEntity<OutgoingTransaction> addOutgoingTransaction(@Valid @RequestBody OutgoingTransaction outgoingTransaction, @PathVariable Long treasuryId) {
        return new ResponseEntity<>(
                outgoingTransactionService.addOutgoingTransaction(outgoingTransaction, treasuryId),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Treasury> getTreasury(@PathVariable Long id) {
        return new ResponseEntity<>(treasuryService.getTreasuryById(id), HttpStatus.OK);
    }

    @GetMapping("/get/{clubId}/club")
    public ResponseEntity<Treasury> getTreasuryForClub(@PathVariable Long clubId) {
        return new ResponseEntity<>(treasuryService.getTreasuryByClubId(clubId), HttpStatus.OK);
    }

    @GetMapping("/get/all/{treasuryId}/incoming")
    public ResponseEntity<List<IncomingTransaction>> getAllIncomingTransactionsForTreasury(@PathVariable Long treasuryId) {
        return new ResponseEntity<>(treasuryService.getTreasuryById(treasuryId).getIncomingTransactions(), HttpStatus.OK);
    }

    @GetMapping("/get/all/{treasuryId}/outgoing")
    public ResponseEntity<List<OutgoingTransaction>> getAllOutgoingTransactionsForTreasury(@PathVariable Long treasuryId) {
        return new ResponseEntity<>(treasuryService.getTreasuryById(treasuryId).getOutgoingTransactions(), HttpStatus.OK);
    }

    @GetMapping("/get/{treasuryId}/balance")
    public ResponseEntity<Map> getFinancialBalance(@PathVariable Long treasuryId) {
        Map<String, Double> stats = new HashMap<>();

        List<IncomingTransaction> incomingTransactions = treasuryService.getTreasuryById(treasuryId).getIncomingTransactions();
        List<OutgoingTransaction> outgoingTransactions = treasuryService.getTreasuryById(treasuryId).getOutgoingTransactions();

        double earnings = incomingTransactions.stream().mapToDouble(IncomingTransaction::getAmount).sum();
        double expenses = outgoingTransactions.stream().mapToDouble(OutgoingTransaction::getAmount).sum();
        double balance = earnings - expenses;

        stats.put("earnings", earnings);
        stats.put("expenses", expenses);
        stats.put("balance", balance);

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

}
