package io.fincorp.tiny_ledger.repository;

import io.fincorp.tiny_ledger.model.LedgerTransaction;
import io.fincorp.tiny_ledger.model.TransactionType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static java.util.Collections.emptyList;

/**
 * {@link LedgerRepository} implementation backed by a {@link ConcurrentHashMap}
 *
 * @author carloscarvalho
 */
@Repository
public class MapLedgerRepository implements LedgerRepository {

    public static final Function<LedgerTransaction, BigDecimal> EXTRACT_TRANSACTION_AMOUNT = value -> TransactionType.DEPOSIT.equals(value.transactionType()) ? value.amount() : value.amount().negate();
    // Simple structure backing account ephemeral persistence.
    private final ConcurrentHashMap<String, ArrayList<LedgerTransaction>> accountsStore = new ConcurrentHashMap<>();

    @Override
    public List<LedgerTransaction> getAccountTransactions(String accountId, int startIndex, int numberOfRecords) {
        return accountsStore.containsKey(accountId) ?
                getTransactionList(accountId, startIndex, numberOfRecords) : emptyList();
    }

    private List<LedgerTransaction> getTransactionList(String accountId, int startIndex, int numberOfRecords) {
        ArrayList<LedgerTransaction> ledgerTransactions = accountsStore.get(accountId);
        // ensure that we don't exceed list size while slicing it
        int listSize = Math.min(startIndex + numberOfRecords, ledgerTransactions.size());
        return ledgerTransactions.subList(startIndex, listSize);
    }

    @Override
    public BigDecimal getAccountBalance(String accountId) {
        return accountsStore.containsKey(accountId) ?
                accountsStore.get(accountId)
                        .stream()
                        .map(EXTRACT_TRANSACTION_AMOUNT)
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO) : BigDecimal.ZERO;
    }

    @Override
    public void storeAccountTransaction(String accountId, LedgerTransaction transaction) {
        if (!accountsStore.containsKey(accountId)) {
            accountsStore.put(accountId, new ArrayList<>());
        }

        accountsStore.get(accountId).add(transaction);
    }
}
