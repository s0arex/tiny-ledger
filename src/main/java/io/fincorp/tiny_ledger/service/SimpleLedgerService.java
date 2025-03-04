package io.fincorp.tiny_ledger.service;

import io.fincorp.tiny_ledger.exception.NegativeAccountBalanceException;
import io.fincorp.tiny_ledger.model.LedgerTransaction;
import io.fincorp.tiny_ledger.model.TransactionRequestEnvelope;
import io.fincorp.tiny_ledger.model.TransactionResult;
import io.fincorp.tiny_ledger.model.TransactionType;
import io.fincorp.tiny_ledger.repository.LedgerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Simple implementation of {@link LedgerService}.
 *
 * @author carloscarvalho
 */
@Service
public class SimpleLedgerService implements LedgerService {
    private final LedgerRepository ledgerRepository;

    public SimpleLedgerService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    @Override
    public List<LedgerTransaction> getTransactions(String accountId, int startIndex, int nrOfRecords) {
        return ledgerRepository.getAccountTransactions(accountId, startIndex, nrOfRecords);
    }

    @Override
    public String getAccountBalance(String accountId) {
        return ledgerRepository.getAccountBalance(accountId).toString();
    }

    @Override
    public TransactionResult processAccountTransaction(String accountId, TransactionRequestEnvelope transactionDetails) {
        LedgerTransaction transaction = new LedgerTransaction(
                UUID.randomUUID().toString(), // simulate the creation of a transaction reference
                transactionDetails.transactionType(),
                new BigDecimal(transactionDetails.amount()).abs(),
                System.currentTimeMillis());

        if (isInvalidWithdrawal(accountId, transaction)) {
            throw new NegativeAccountBalanceException("Insufficient balance to perform transaction");
        }

        ledgerRepository.storeAccountTransaction(accountId, transaction);

        return new TransactionResult(transaction.id(), ledgerRepository.getAccountBalance(accountId).toString());
    }

    private boolean isInvalidWithdrawal(String accountId, LedgerTransaction transaction) {
        return TransactionType.WITHDRAWAL.equals(transaction.transactionType()) &&
                ledgerRepository.getAccountBalance(accountId).subtract(transaction.amount())
                        .compareTo(BigDecimal.ZERO) < 0;
    }
}
