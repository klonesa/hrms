package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.EmployeeService;
import com.bayrak.hrms.core.utilities.results.*;
import com.bayrak.hrms.repository.EmployeeDao;
import com.bayrak.hrms.entity.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeManager implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    @Override
    public Result save(Employee employee) {
        employeeDao.save(employee);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<Employee>> findAll() {
        return new SuccessDataResult<>(employeeDao.findAll());
    }

    @Override
    public DataResult<Optional<Employee>> findById(int id) {
        return new SuccessDataResult<>(employeeDao.findById(id));
    }

    @Override
    public Result deleteById(int id) {
        if(employeeDao.existsById(id)){
            employeeDao.deleteById(id);
            return new SuccessResult("Deleted succesfully");
        }
        return new ErrorResult("Employee not found");

    }
}
