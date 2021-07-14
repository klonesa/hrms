package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.VerificationCodeCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeCandidateDao extends JpaRepository<VerificationCodeCandidate, Integer> {
    Optional<VerificationCodeCandidate> findByCandidateId(int id);
}
