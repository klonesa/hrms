package com.bayrak.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class ResumeAlreadyHaveImageException extends RuntimeException {
    public ResumeAlreadyHaveImageException(int id) {
        super("This resume already have a photo , id : " + id);
    }
}
