package io.fincorp.tiny_ledger.model;

/**
 * Request envelope for ledger transaction requests.
 *
 * @param transactionId - The unique identifier of the transaction processed in the scope of current request.
 * @param currentBalance - The account balance that resulted from applying the transaction.
 */
public record TransactionResult(String transactionId, String currentBalance) {
}
