package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.CandidateService;
import com.bayrak.hrms.business.abstracts.VerificationCodeCandidateService;
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

    private final CandidateDao candidateDao;
    private final VerifyService<Candidate> candidateVerifyService;
    private final VerificationCodeCandidateService verificationCodeCandidateService;


    @Autowired
    public CandidateManager(CandidateDao candidateDao, VerifyService<Candidate> candidateVerifyService
    ,VerificationCodeCandidateService verificationCodeCandidateService) {
        this.candidateDao = candidateDao;
        this.candidateVerifyService = candidateVerifyService;
        this.verificationCodeCandidateService=verificationCodeCandidateService;
    }
    
    
    @Override
    public Result save(Candidate candidate) {

        Result result = candidateVerifyService.verify(candidate);
        if(result.isSuccess()){
            candidateDao.save(candidate);
            verificationCodeCandidateService.generateCode(candidate.getId());

        }
        return result;

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
