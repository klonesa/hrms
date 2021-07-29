package com.bayrak.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DeleteImageErrorException extends RuntimeException {
    public DeleteImageErrorException(String message) {
        super(message);
    }
}