package com.bayrak.hrms.service;

import com.bayrak.hrms.exception.MernisVerificationException;
import com.bayrak.hrms.utils.adapters.CandidateMernisCheck;
import com.bayrak.hrms.utils.results.SuccessResult;
import com.bayrak.hrms.model.Candidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyCandidateService implements VerifyService<Candidate> {

    private final CandidateMernisCheck mernisCheck;

    @Override
    public boolean verify(Candidate data) {
        if (!mernisCheck.check(data)) {
            throw new MernisVerificationException("User information not valid");
        }
        return true;
    }
}
