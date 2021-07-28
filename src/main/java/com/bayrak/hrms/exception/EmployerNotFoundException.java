package com.bayrak.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployerNotFoundException extends RuntimeException {
    public EmployerNotFoundException(int employerId) {
        super("Employer not found , id :" + employerId);
    }
}
