package com.bayrak.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmployerDomainMisMatchException extends RuntimeException {
    public EmployerDomainMisMatchException(String message) {
        super(message);
    }
}