package io.fincorp.tiny_ledger.model;

/**
 * Request envelope for ledger transaction requests.
 *
 * @param transactionType - The type of transaction, represented by {@link TransactionType} enum.
 * @param amount - The amount of money involved in the transaction request.
 */
public record TransactionRequestEnvelope(TransactionType transactionType, String amount) {
}
