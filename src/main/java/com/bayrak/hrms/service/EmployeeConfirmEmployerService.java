package com.bayrak.hrms.service;

import com.bayrak.hrms.dto.convertor.EmployeeConvertor;
import com.bayrak.hrms.dto.convertor.EmployerConvertor;
import com.bayrak.hrms.model.EmployeeConfirmEmployer;
import com.bayrak.hrms.repository.EmployeeConfirmEmployerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeConfirmEmployerService {

    private final EmployeeConfirmEmployerDao employeeConfirmEmployerDao;
    private final EmployerService employerService;
    private final EmployeeService employeeService;
    private final EmployeeConvertor employeeConvertor;

    public EmployeeConfirmEmployer confirmEmployer(int employeeId, int employerId) {
        return employeeConfirmEmployerDao.save(
                new EmployeeConfirmEmployer(
                        employeeConvertor.DtoToEntity(employeeService.findById(employeeId)),
                        employerService.findById(employerId)));
    }
}
