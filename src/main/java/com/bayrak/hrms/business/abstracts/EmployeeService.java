package com.bayrak.hrms.business.abstracts;

import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.entity.concretes.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Result save(Employee candidate);
    DataResult<List<Employee>> findAll();
    DataResult<Optional<Employee>> findById(int id);
    Result deleteById(int id);
}
