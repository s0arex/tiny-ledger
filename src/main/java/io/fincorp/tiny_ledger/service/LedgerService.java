package io.fincorp.tiny_ledger.service;

import io.fincorp.tiny_ledger.exception.NegativeAccountBalanceException;
import io.fincorp.tiny_ledger.model.LedgerTransaction;
import io.fincorp.tiny_ledger.model.TransactionRequestEnvelope;
import io.fincorp.tiny_ledger.model.TransactionResult;

import java.util.List;

/**
 * Interface for ledger component implementations holding business logic
 *
 * @author carloscarvalho
 */
public interface LedgerService {
    /**
     * Retrieves a subset of the transactions for a given account.
     *
     * @param accountId   - Unique identifier for the customer account.
     * @param startIndex  - The starting index for fetching the transaction list.
     * @param nrOfRecords - Number of transactions to retrieve, starting from the startIndex.
     * @return list of transactions for the specified account, up to the numberOfRecords
     */
    List<LedgerTransaction> getTransactions(String accountId, int startIndex, int nrOfRecords);

    /**
     * Retrieves the account balance for a specified customer account.
     *
     * @param accountId - Unique identifier for the customer account.
     * @return the account balance value.
     */
    String getAccountBalance(String accountId);

    /**
     * Processes the given transaction for a specified customer account.
     *
     * @param accountId   - Unique identifier for the customer account.
     * @param transaction - transaction details.
     * @return the transaction result including its unique identifier and account balance.
     * @throws NegativeAccountBalanceException - if applying the transaction would result in a negative account balance.
     */
    TransactionResult processAccountTransaction(String accountId, TransactionRequestEnvelope transaction);
}
