package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.VerificationCodeCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeCandidateDao extends JpaRepository<VerificationCodeCandidate, Integer> {
    Optional<VerificationCodeCandidate> findByCandidateId(int id);
}
