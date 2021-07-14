package com.bayrak.hrms.business.abstracts;

import com.bayrak.hrms.core.utilities.results.Result;

public interface EmployeeConfirmEmployerService {

    Result confirmEmployer(int employeeId, int employerId);
}
