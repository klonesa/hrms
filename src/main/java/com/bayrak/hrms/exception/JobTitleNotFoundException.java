package com.bayrak.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class JobTitleNotFoundException extends RuntimeException {
    public JobTitleNotFoundException(int jobTitleId) {
        super("Job title not found," + " id : " + jobTitleId);
    }
}