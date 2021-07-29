package com.bayrak.hrms.service;

import com.bayrak.hrms.dto.EmployerDto;
import com.bayrak.hrms.exception.EmailAlreadyTakenException;
import com.bayrak.hrms.exception.EmployerDomainMisMatchException;
import com.bayrak.hrms.model.Employer;
import com.bayrak.hrms.repository.EmployerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VerifyEmployerService implements VerifyService<EmployerDto> {

    private final EmployerDao employerDao;

    @Override
    public boolean verify(EmployerDto data) {
        if (!data.getWebAdress().contains(getDomain(data))) {
            throw new EmployerDomainMisMatchException("Email domain and web adress should match");
        }
        if(employerDao.existsByEmail(data.getEmail())){
            throw new EmailAlreadyTakenException("Email already taken : " + data.getEmail());
        }
        return true;
    }

    private String getDomain(EmployerDto data) {
        return data.getEmail().split("@")[1];
    }
}
