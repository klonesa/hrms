package com.bayrak.hrms.business.abstracts;

import com.bayrak.hrms.entity.concretes.Employer;

import java.util.List;
import java.util.Optional;

public interface EmployerService {
    void save(Employer employer);
    List<Employer> findAll();
    Optional<Employer> findById(int id);

}
