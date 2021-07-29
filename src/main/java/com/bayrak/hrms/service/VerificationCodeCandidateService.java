package com.bayrak.hrms.service;

import com.bayrak.hrms.exception.CandidateNotFoundException;
import com.bayrak.hrms.exception.VerificationCodeMisMatchException;
import com.bayrak.hrms.model.Candidate;
import com.bayrak.hrms.model.VerificationCodeCandidate;
import com.bayrak.hrms.repository.VerificationCodeCandidateDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class VerificationCodeCandidateService {

    private final VerificationCodeCandidateDao verificationCodeCandidateDao;

    protected String generateCode(int candidateId,Candidate candidate) {

        String code = UUID.randomUUID().toString();

        verificationCodeCandidateDao.findByCandidateId(candidateId).ifPresentOrElse(
                i -> {
                    i.setCode(code);
                    verificationCodeCandidateDao.save(i);
                },
                () -> {
                    verificationCodeCandidateDao.save(
                            new VerificationCodeCandidate(code ,
                                    candidate));
                }
        );
        return code;
    }

    public String getLastCode(int candidateId) {
        return verificationCodeCandidateDao.findByCandidateId(candidateId)
                .orElseThrow(
                        ()-> {
                            throw new CandidateNotFoundException(candidateId);
                        })
                .getCode();
    }

    public void verify(int candidateId, String code) {
        String code2 = getLastCode(candidateId);

        verificationCodeCandidateDao.findByCandidateId(candidateId)
                .ifPresentOrElse(
                        i -> {
                            if (code2.equals(code)) {
                                i.setVerified(true);
                                i.setVerifiedDate(new Date());
                                verificationCodeCandidateDao.save(i);
                            }else{
                                throw new VerificationCodeMisMatchException(code);
                            }
                        },() -> {
                    throw new CandidateNotFoundException(candidateId);
                });
    }

}
