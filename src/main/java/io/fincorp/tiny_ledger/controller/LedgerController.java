package io.fincorp.tiny_ledger.controller;

import io.fincorp.tiny_ledger.model.LedgerTransaction;
import io.fincorp.tiny_ledger.model.TransactionRequestEnvelope;
import io.fincorp.tiny_ledger.model.TransactionResult;
import io.fincorp.tiny_ledger.service.LedgerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller exposing ledger endpoints
 *
 * @author carloscarvalho
 */
@RestController
@RequestMapping("ledger/accounts")
public class LedgerController {
    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @GetMapping("/{accountId}/balance")
    public String viewBalance(@PathVariable String accountId) {
        return ledgerService.getAccountBalance(accountId);
    }

    @GetMapping("/{accountId}/transactions")
    public List<LedgerTransaction> viewHistory(@PathVariable String accountId,
                                               @RequestParam(defaultValue = "0") int startIndex,
                                               @RequestParam(defaultValue = "10") int nrOfRecords) {
        return ledgerService.getTransactions(accountId, startIndex, nrOfRecords);
    }

    @PostMapping("/{accountId}/transactions")
    public TransactionResult transaction(
            @PathVariable String accountId,
            @RequestBody TransactionRequestEnvelope transactionDetails) {

        return ledgerService.processAccountTransaction(accountId, transactionDetails);
    }
}
