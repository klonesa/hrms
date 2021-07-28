package com.bayrak.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UploadImageErrorException extends RuntimeException {
    public UploadImageErrorException(String message) {
        super(message);
    }
}