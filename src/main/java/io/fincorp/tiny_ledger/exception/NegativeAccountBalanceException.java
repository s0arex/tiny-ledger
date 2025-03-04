package io.fincorp.tiny_ledger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NegativeAccountBalanceException extends RuntimeException {
    public NegativeAccountBalanceException(String s) {
        super(s);
    }
}
