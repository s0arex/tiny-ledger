package io.fincorp.tiny_ledger.exception;

import io.fincorp.tiny_ledger.model.ErrorResponseEnvelope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LedgerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NegativeAccountBalanceException.class})
    protected ResponseEntity<Object> handleInvalidAccountBalance(RuntimeException ex, WebRequest request) {
        ErrorResponseEnvelope responseBody = new ErrorResponseEnvelope(
                "INVALID_BALANCE",
                "Account balance can't be negative");
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // other typed exceptions might be added here to improve server-client error communication, offering a better experience
    // to API consumers
    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleInternalErrors(RuntimeException ex, WebRequest request) {
        ErrorResponseEnvelope responseBody = new ErrorResponseEnvelope(
                "GENERAL_ERROR",
                "An error occurred while processing the request");
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
