package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {

}
