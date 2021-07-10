package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.CandidateService;
import com.bayrak.hrms.dataAccess.abstracts.CandidateDao;
import com.bayrak.hrms.entity.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateManager implements CandidateService {

    private CandidateDao candidateDao;

    @Autowired
    public CandidateManager(CandidateDao candidateDao) {
        this.candidateDao = candidateDao;
    }
    
    
    @Override
    public void save(Candidate candidate) {
        candidateDao.save(candidate);
    }

    @Override
    public List<Candidate> findAll() {
        return candidateDao.findAll();
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return candidateDao.findById(id);
    }
}
