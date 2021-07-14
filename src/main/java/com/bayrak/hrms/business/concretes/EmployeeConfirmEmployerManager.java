package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.EmployeeConfirmEmployerService;
import com.bayrak.hrms.business.abstracts.EmployeeService;
import com.bayrak.hrms.business.abstracts.EmployerService;
import com.bayrak.hrms.core.utilities.results.ErrorResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.core.utilities.results.SuccessResult;
import com.bayrak.hrms.dataAccess.abstracts.EmployeeConfirmEmployerDao;
import com.bayrak.hrms.entity.concretes.Employee;
import com.bayrak.hrms.entity.concretes.EmployeeConfirmEmployer;
import com.bayrak.hrms.entity.concretes.Employer;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConfirmEmployerManager implements EmployeeConfirmEmployerService {

    private final EmployeeConfirmEmployerDao employeeConfirmEmployerDao;
    private final EmployerService employerService;
    private final EmployeeService employeeService;

    public EmployeeConfirmEmployerManager(EmployeeConfirmEmployerDao employeeConfirmEmployerDao, EmployerService employerService, EmployeeService employeeService) {
        this.employeeConfirmEmployerDao = employeeConfirmEmployerDao;
        this.employerService = employerService;
        this.employeeService = employeeService;
    }

    @Override
    public Result confirmEmployer(int employeeId, int employerId) {
        Employee employee;
        Employer employer;
        if (employeeService.findById(employeeId).getData().isEmpty()) {
            return new ErrorResult("Employer not found");
        }
        employee = employeeService.findById(employeeId).getData().get();

        if(employerService.findById(employerId).isEmpty()){
            return new ErrorResult("Employee not found");
        }
        employer = employerService.findById(employerId).get();

        employeeConfirmEmployerDao.save(
                new EmployeeConfirmEmployer(employee, employer));
        return new SuccessResult("employer confirmed succesfully "+employee.getFirstName());
    }

}
