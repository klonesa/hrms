package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.VerificationCodeEmployer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeEmployerDao extends JpaRepository<VerificationCodeEmployer, Integer> {
    Optional<VerificationCodeEmployer> findByEmployerId(int id);


}