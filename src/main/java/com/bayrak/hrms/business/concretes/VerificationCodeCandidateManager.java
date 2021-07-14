package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.VerificationCodeCandidateService;
import com.bayrak.hrms.core.utilities.results.*;
import com.bayrak.hrms.dataAccess.abstracts.CandidateDao;
import com.bayrak.hrms.dataAccess.abstracts.VerificationCodeCandidateDao;
import com.bayrak.hrms.entity.concretes.Candidate;
import com.bayrak.hrms.entity.concretes.VerificationCodeCandidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationCodeCandidateManager implements VerificationCodeCandidateService {

    private final VerificationCodeCandidateDao verificationCodeCandidateDao;
    private final CandidateDao candidateDao;

    @Autowired
    public VerificationCodeCandidateManager(VerificationCodeCandidateDao verificationCodeCandidateDao,
                                            CandidateDao candidateDao) {
        this.verificationCodeCandidateDao = verificationCodeCandidateDao;
        this.candidateDao =candidateDao;
    }

    @Override
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

    @Override
    public DataResult<String> getLastCode(int candidateId) {
        Optional<VerificationCodeCandidate> vcc =
                verificationCodeCandidateDao.findByCandidateId(candidateId);
        if(vcc.isPresent()){
            return new SuccessDataResult<>(vcc.get().getCode());
        }

        return new ErrorDataResult("Candidate not found");
    }

    @Override
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
