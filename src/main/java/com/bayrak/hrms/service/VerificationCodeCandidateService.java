package com.bayrak.hrms.service;

import com.bayrak.hrms.model.Candidate;
import com.bayrak.hrms.model.VerificationCodeCandidate;
import com.bayrak.hrms.repository.CandidateDao;
import com.bayrak.hrms.repository.VerificationCodeCandidateDao;
import com.bayrak.hrms.utils.results.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationCodeCandidateService {

    private final VerificationCodeCandidateDao verificationCodeCandidateDao;
    private final CandidateDao candidateDao;

    public DataResult<String> generateCode(int candidateId) {

        if (!candidateDao.existsById(candidateId)) {
            return new ErrorDataResult("Candidate not found");
        }

        Candidate candidate=candidateDao.getById(candidateId);
        Optional<VerificationCodeCandidate> ovcc=
                verificationCodeCandidateDao.findByCandidateId(candidateId);

        String code = UUID.randomUUID().toString();

        if(ovcc.isEmpty()){
            verificationCodeCandidateDao.save(
                    new VerificationCodeCandidate(code ,
                            candidate));
            return new SuccessDataResult<>(code);
        }
        ovcc.get().setCode(code);
        verificationCodeCandidateDao.save(ovcc.get());
        return new SuccessDataResult<>(code);
    }

    public DataResult<String> getLastCode(int candidateId) {
        Optional<VerificationCodeCandidate> vcc =
                verificationCodeCandidateDao.findByCandidateId(candidateId);
        if(vcc.isPresent()){
            return new SuccessDataResult<>(vcc.get().getCode());
        }

        return new ErrorDataResult("Candidate not found");
    }

    public Result verify(int candidateId, String code) {
        if(!getLastCode(candidateId).isSuccess()){
            generateCode(candidateId);
        }
        String code2 = getLastCode(candidateId).getData();

        Optional<VerificationCodeCandidate> ovcc = verificationCodeCandidateDao.findByCandidateId(candidateId);

        if(ovcc.isEmpty()){
            return new ErrorResult("Candidate not found");
        }

        VerificationCodeCandidate vce = ovcc.get();
        vce.setVerified(true);
        vce.setVerifiedDate(new Date());
        if(code2.equals(code)){
            verificationCodeCandidateDao.save(vce);
            return new SuccessResult("Candidate validated successfully");
        }
        return new ErrorResult("Verification code doesn't match");
    }

}
