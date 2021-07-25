package com.bayrak.hrms.repository;

import com.bayrak.hrms.entity.concretes.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {
    boolean existsByEmail(String email);
    boolean existsById(int id);
}
