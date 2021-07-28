package com.bayrak.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class ResumeDoesNotHaveImageException extends RuntimeException {
    public ResumeDoesNotHaveImageException(int id) {
        super("This resume does not have a photo , id : " + id);
    }
}
