package com.bayrak.hrms.business.abstracts;

import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.entity.concretes.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    Result save(Candidate candidate);
    List<Candidate> findAll();
    Optional<Candidate> findById(int id);
}
