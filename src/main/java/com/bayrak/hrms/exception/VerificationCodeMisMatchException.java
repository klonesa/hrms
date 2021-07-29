package com.bayrak.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT)
public class VerificationCodeMisMatchException extends RuntimeException {
    public VerificationCodeMisMatchException(String message) {
        super("Verification code does not match : ");
    }
}