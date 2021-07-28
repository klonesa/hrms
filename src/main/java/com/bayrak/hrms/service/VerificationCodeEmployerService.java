package com.bayrak.hrms.service;

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
    private final EmployerDao employerDao;

    public DataResult<String> generateCode(int employerId) {

        if (!employerDao.existsById(employerId)) {
            return new ErrorDataResult("Employer not found");
        }

        Employer employer = employerDao.getById(employerId);

        Optional<VerificationCodeEmployer> ovce=
                verificationCodeEmployerDao.findByEmployerId(employerId);

        String code = UUID.randomUUID().toString();

        if(ovce.isEmpty()){
            verificationCodeEmployerDao.save(
                    new VerificationCodeEmployer(code ,
                            employer));
            return new SuccessDataResult<>(code);
        }
            ovce.get().setCode(code);
            verificationCodeEmployerDao.save(ovce.get());
        return new SuccessDataResult<>(code);
    }

    public DataResult<String> getLastCode(int employerId) {
        Optional<VerificationCodeEmployer> vce =
                verificationCodeEmployerDao.findByEmployerId(employerId);
        if(vce.isPresent()){
            return new SuccessDataResult<>(vce.get().getCode());
        }

       return new ErrorDataResult("Employer not found");
    }

    public Result verify(int employerId,String code) {

        if(!getLastCode(employerId).isSuccess()){
            generateCode(employerId);
        }
        String code2 = getLastCode(employerId).getData();

        Optional<VerificationCodeEmployer> ovce = verificationCodeEmployerDao.findByEmployerId(employerId);

        if(ovce.isEmpty()){
            return new ErrorResult("Employer not found");
        }

        VerificationCodeEmployer vce = ovce.get();
        vce.setVerified(true);
        vce.setVerifiedDate(new Date());
        if(code2.equals(code)){
            verificationCodeEmployerDao.save(vce);
            return new SuccessResult("Employer validated successfully");
        }
        return new ErrorResult("Verification code doesn't match");
    }

}
