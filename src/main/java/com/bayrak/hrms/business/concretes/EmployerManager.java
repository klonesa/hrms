package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.EmployerService;
import com.bayrak.hrms.business.abstracts.VerificationCodeEmployerService;
import com.bayrak.hrms.repository.EmployerDao;
import com.bayrak.hrms.entity.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final VerificationCodeEmployerService verificationCodeEmployerService;

    @Autowired
    public EmployerManager(EmployerDao employerDao, VerificationCodeEmployerService verificationCodeEmployerService) {
        this.employerDao = employerDao;
        this.verificationCodeEmployerService = verificationCodeEmployerService;
    }

    @Override
    public void save(Employer employer) {
        employerDao.save(employer);
        verificationCodeEmployerService.generateCode(employer.getId());
    }

    @Override
    public List<Employer> findAll() {
        return employerDao.findAll();
    }

    @Override
    public Optional<Employer> findById(int id) {
        return employerDao.findById(id);
    }
}
