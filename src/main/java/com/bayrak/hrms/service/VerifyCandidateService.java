package com.bayrak.hrms.service;

import com.bayrak.hrms.dto.CandidateDto;
import com.bayrak.hrms.dto.convertor.CandidateConverter;
import com.bayrak.hrms.exception.MernisVerificationException;
import com.bayrak.hrms.utils.adapters.CandidateMernisCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyCandidateService implements VerifyService<CandidateDto> {

    private final CandidateMernisCheck mernisCheck;
    private final CandidateConverter candidateConverter;

    @Override
    public boolean verify(CandidateDto data) {

        if (!mernisCheck.check(candidateConverter.DtoToEntity(data))) {
            throw new MernisVerificationException("User information not valid");
        }
        return true;
    }
}
