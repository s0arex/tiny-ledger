/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package io.fincorp.tiny_ledger.model;

import java.math.BigDecimal;

/**
 * Represents a transaction associated with a customer.
 *
 * @param id              - Unique identifier of the transaction.
 * @param transactionType - The type of transaction, represented by the {@link TransactionType} enum.
 * @param amount          - The amount of money involved in the transaction request.
 * @param timestamp       - The moment when the transaction occurred, in milliseconds since the epoch.
 */
public record LedgerTransaction(String id, TransactionType transactionType, BigDecimal amount, long timestamp) {
}


