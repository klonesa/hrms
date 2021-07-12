package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.CandidateService;
import com.bayrak.hrms.business.abstracts.VerifyService;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.dataAccess.abstracts.CandidateDao;
import com.bayrak.hrms.entity.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateManager implements CandidateService {

    private CandidateDao candidateDao;
    private VerifyService<Candidate> candidateVerifyService;

    @Autowired
    public CandidateManager(CandidateDao candidateDao,VerifyService<Candidate> candidateVerifyService) {
        this.candidateDao = candidateDao;
        this.candidateVerifyService = candidateVerifyService;
    }
    
    
    @Override
    public Result save(Candidate candidate) {

        Result result = candidateVerifyService.verify(candidate);
        if(result.isSuccess()){
            candidateDao.save(candidate);
            System.out.println("success");
            return result;

        }else{
            System.out.println("else");
            return result;
        }

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
