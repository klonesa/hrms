package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.VerificationCodeEmployer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeEmployerDao extends JpaRepository<VerificationCodeEmployer, Integer> {
    Optional<VerificationCodeEmployer> findByEmployerId(int id);
}