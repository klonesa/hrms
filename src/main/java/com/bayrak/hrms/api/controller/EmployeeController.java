package com.bayrak.hrms.api.controller;

import com.bayrak.hrms.business.abstracts.EmployeeService;
import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.core.utilities.results.SuccessResult;
import com.bayrak.hrms.entity.concretes.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public DataResult<List<Employee>> getAll(){
        return employeeService.findAll();
    }

    @GetMapping("{id}")
    public DataResult<Optional<Employee>> getById(@PathVariable int id) {
        Employee newEmployee =
               employeeService.findById(id).getData().orElseThrow(()-> new RuntimeException("Not found"));
        return employeeService.findById(id);
    }

    @PostMapping
    public Result put(@RequestBody Employee employee){
        employeeService.save(employee);

        return new SuccessResult();
    }

    @PutMapping("{id}")
    Result replaceEmployee(@RequestBody Employee newEmployee,
                             @PathVariable int id) {

        return employeeService.findById(id).getData()
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
