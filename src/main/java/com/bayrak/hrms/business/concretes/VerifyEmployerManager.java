package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.VerifyService;
import com.bayrak.hrms.core.utilities.results.ErrorResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.core.utilities.results.SuccessResult;
import com.bayrak.hrms.repository.EmployerDao;
import com.bayrak.hrms.entity.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyEmployerManager implements VerifyService<Employer> {


    private final EmployerDao employerDao;

    @Autowired
    public VerifyEmployerManager(EmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    @Override
    public Result verify(Employer data) {

        String string = data.getEmail();
        String[] parts = string.split("@");
        String domain = parts[1];

        if (!data.getWebAdress().contains(domain)) {
            return new ErrorResult("Email domain and web adress should match");
        }
        if(employerDao.existsByEmail(data.getEmail())){
            return new ErrorResult("Email already taken");
        }

        return new SuccessResult("Employer saved successfully. Please confirm your email adress");
    }
}
