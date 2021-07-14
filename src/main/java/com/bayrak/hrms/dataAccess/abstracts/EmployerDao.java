package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer,Integer> {
    boolean existsByEmail(String email);
    boolean existsById(int id);
}
