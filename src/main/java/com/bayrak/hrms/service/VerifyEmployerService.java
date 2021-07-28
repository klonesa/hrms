package com.bayrak.hrms.service;

import com.bayrak.hrms.exception.EmailAlreadyTakenException;
import com.bayrak.hrms.exception.EmployerDomainMisMatchException;
import com.bayrak.hrms.model.Employer;
import com.bayrak.hrms.repository.EmployerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyEmployerService implements VerifyService<Employer> {


    private final EmployerDao employerDao;

    @Autowired
    public VerifyEmployerService(EmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    @Override
    public boolean verify(Employer data) {
        if (!data.getWebAdress().contains(getDomain(data))) {
            throw new EmployerDomainMisMatchException("Email domain and web adress should match");
        }
        if(employerDao.existsByEmail(data.getEmail())){
            throw new EmailAlreadyTakenException("Email already taken : " + data.getEmail());
        }
        return true;
    }

    private String getDomain(Employer data) {
        return data.getEmail().split("@")[1];
    }
}
