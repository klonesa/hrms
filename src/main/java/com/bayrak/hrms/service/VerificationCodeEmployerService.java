package com.bayrak.hrms.service;

import com.bayrak.hrms.exception.CandidateNotFoundException;
import com.bayrak.hrms.exception.EmployerNotFoundException;
import com.bayrak.hrms.exception.VerificationCodeMisMatchException;
import com.bayrak.hrms.model.Employer;
import com.bayrak.hrms.model.VerificationCodeEmployer;
import com.bayrak.hrms.repository.EmployerDao;
import com.bayrak.hrms.repository.VerificationCodeEmployerDao;
import com.bayrak.hrms.utils.results.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationCodeEmployerService {


    private final VerificationCodeEmployerDao verificationCodeEmployerDao;

    protected String generateCode(int employerId, Employer employer) {

        String code = UUID.randomUUID().toString();

        verificationCodeEmployerDao.findByEmployerId(employerId)
                .ifPresentOrElse(
                        i -> {
                            i.setCode(code);
                        },() -> {
                            verificationCodeEmployerDao.save(
                                    new VerificationCodeEmployer(
                                            code,employer
                                    ));
                        }
                );
        return code;
    }

    public String getLastCode(int employerId) {

        return verificationCodeEmployerDao.findByEmployerId(employerId)
                .orElseThrow(() -> {
                    throw new EmployerNotFoundException(employerId);
                }).getCode();
    }

    public void verify(int employerId,String code) {
        String code2 = getLastCode(employerId);

        verificationCodeEmployerDao.findByEmployerId(employerId)
            .ifPresentOrElse(
                    i -> {
                        if (code2.equals(code)) {
                            i.setVerified(true);
                            i.setVerifiedDate(new Date());
                            verificationCodeEmployerDao.save(i);
                        }else{
                            throw new VerificationCodeMisMatchException(code);
                        }
                    },() -> {
                        throw new EmployerNotFoundException(employerId);
                    });
    }

}
