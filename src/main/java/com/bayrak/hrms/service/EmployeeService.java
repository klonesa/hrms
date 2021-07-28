package com.bayrak.hrms.service;

import com.bayrak.hrms.exception.EmployeeNotFoundException;
import com.bayrak.hrms.model.Employee;
import com.bayrak.hrms.repository.EmployeeDao;
import com.bayrak.hrms.utils.results.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeDao employeeDao;

    public Result save(Employee employee) {
        employeeDao.save(employee);
        return new SuccessResult();
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    public Optional<Employee> findByIdOptional(int id) {
        return employeeDao.findById(id);
        };

    public Employee findById(int id) {
        return employeeDao.findById(id).orElseThrow(()-> {
            throw new EmployeeNotFoundException(id);
        });
    };


    public Result deleteById(int id) {
        if (employeeDao.existsById(id)) {
            employeeDao.deleteById(id);
            return new SuccessResult();
        }
        throw new EmployeeNotFoundException(id);
    }
}
