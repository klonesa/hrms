package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.adapters.concretes.CandidateMernisCheck;
import com.bayrak.hrms.business.abstracts.VerifyService;
import com.bayrak.hrms.core.utilities.results.ErrorResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.core.utilities.results.SuccessResult;
import com.bayrak.hrms.dataAccess.abstracts.CandidateDao;
import com.bayrak.hrms.entity.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyCandidateManager implements VerifyService<Candidate> {

    private final CandidateMernisCheck mernisCheck;
    private final CandidateDao candidateDao;

    @Autowired
    public VerifyCandidateManager(CandidateMernisCheck mernisCheck, CandidateDao candidateDao) {
        this.mernisCheck = mernisCheck;
        this.candidateDao = candidateDao;
    }

    @Override
    public Result verify(Candidate data) {
        if(candidateDao.existsByEmail(data.getEmail())){
            return new ErrorResult("Email already taken");
        }
        if (!mernisCheck.check(data)) {
            return new ErrorResult("Identity informations not valid");
        }

        
        return new SuccessResult("User validated");
    }
}
