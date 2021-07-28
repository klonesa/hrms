package com.bayrak.hrms.controller;

import com.bayrak.hrms.model.Employee;
import com.bayrak.hrms.service.EmployeeService;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import com.bayrak.hrms.utils.results.SuccessResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public DataResult<List<Employee>> getAll(){
        return new SuccessDataResult<>(employeeService.findAll());
    }

    @GetMapping("{id}")
    public DataResult<Employee> getById(@PathVariable int id) {
        return new SuccessDataResult<>(employeeService.findById(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Result put(@RequestBody Employee employee){
        employeeService.save(employee);
        return new SuccessResult();
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    Result replaceEmployee(@RequestBody Employee newEmployee,
                             @PathVariable int id) {

        return employeeService.findByIdOptional(id)
                .map(employee -> {
                    employee.setEmail(newEmployee.getEmail());
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setPassword(newEmployee.getPassword());
                    return employeeService.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeService.save(newEmployee);
                });
    }

    @DeleteMapping("{id}")
    public Result deleteEmployee(@PathVariable int id) {
        return employeeService.deleteById(id);
    }

}
