package io.fincorp.tiny_ledger.repository;

import io.fincorp.tiny_ledger.model.LedgerTransaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for ledger data repositories of all types.
 *
 * @author carloscarvalho
 */
public interface LedgerRepository {
    /**
     * Retrieves a list of transactions for a specified customer account.
     *
     * @param accountId       - Unique identifier for the customer account
     * @param startIndex      - The starting index for fetching the transaction list
     * @param numberOfRecords - Number of transactions to retrieve, starting from the startIndex.
     * @return list of transactions for the specified account, up to the numberOfRecords
     */
    List<LedgerTransaction> getAccountTransactions(String accountId, int startIndex, int numberOfRecords);

    /**
     * Retrieves the current account balance
     *
     * @param accountId - Unique identifier for the customer account.
     * @return current balance
     */
    BigDecimal getAccountBalance(String accountId);

    /**
     * Store the transaction associated with the given accountId.
     *
     * @param accountId   - Unique identifier for the customer account.
     * @param transaction - Transaction to store
     */
    void storeAccountTransaction(String accountId, LedgerTransaction transaction);
}
