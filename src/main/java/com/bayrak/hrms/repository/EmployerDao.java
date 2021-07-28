package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer,Integer> {
    boolean existsByEmail(String email);
}
