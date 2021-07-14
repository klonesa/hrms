package com.bayrak.hrms.business.abstracts;

import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;

public interface VerificationCodeEmployerService {
    DataResult<String> generateCode(int employerId);
    DataResult<String> getLastCode(int employerId);
    Result verify(int employerId,String code);
}
