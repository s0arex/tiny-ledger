package io.fincorp.tiny_ledger.model;

/**
 * Envelope for API error response body
 *
 * @param errorCode    - API error code intended to represent know failure causes.
 * @param errorMessage - Human readable message associated with the error cause.
 */
public record ErrorResponseEnvelope(String errorCode, String errorMessage) {
}
