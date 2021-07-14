package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.EmployeeConfirmEmployer;
import com.bayrak.hrms.entity.concretes.VerificationCodeEmployer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeConfirmEmployerDao extends JpaRepository<EmployeeConfirmEmployer,Integer> {
    VerificationCodeEmployer findByEmployerId(int id);
}
