package com.bayrak.hrms.business.abstracts;

import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;

public interface VerificationCodeCandidateService {
    DataResult<String> generateCode(int candidateId);
    DataResult<String>  getLastCode(int candidateId);
    Result verify(int candidateId,String code);

}
