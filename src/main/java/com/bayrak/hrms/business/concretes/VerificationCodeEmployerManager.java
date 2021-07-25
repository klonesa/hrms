package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.VerificationCodeEmployerService;
import com.bayrak.hrms.core.utilities.results.*;
import com.bayrak.hrms.repository.EmployerDao;
import com.bayrak.hrms.repository.VerificationCodeEmployerDao;
import com.bayrak.hrms.entity.concretes.Employer;
import com.bayrak.hrms.entity.concretes.VerificationCodeEmployer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationCodeEmployerManager implements VerificationCodeEmployerService {

    private final VerificationCodeEmployerDao verificationCodeEmployerDao;
    private final EmployerDao employerDao;

    @Autowired
    public VerificationCodeEmployerManager(VerificationCodeEmployerDao verificationCodeEmployerDao, EmployerDao employerDao) {
        this.verificationCodeEmployerDao = verificationCodeEmployerDao;
        this.employerDao = employerDao;
    }

    @Override
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

    @Override
    public DataResult<String> getLastCode(int employerId) {
        Optional<VerificationCodeEmployer> vce =
                verificationCodeEmployerDao.findByEmployerId(employerId);
        if(vce.isPresent()){
            return new SuccessDataResult<>(vce.get().getCode());
        }

       return new ErrorDataResult("Employer not found");
    }

    @Override
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
