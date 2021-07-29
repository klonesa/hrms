package com.bayrak.hrms.controller;

import com.bayrak.hrms.dto.EmployeeDto;
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
    public DataResult<List<EmployeeDto>> getAll(){
        return new SuccessDataResult<>(employeeService.findAll());
    }

    @GetMapping("{id}")
    public DataResult<EmployeeDto> getById(@PathVariable int id) {
        return new SuccessDataResult<>(employeeService.findById(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Result put(@RequestBody EmployeeDto employeeDto){
        return new SuccessDataResult<>(employeeService.save(employeeDto));
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    Result replaceEmployee(@RequestBody EmployeeDto newEmployee, @PathVariable int id) {
        return new SuccessDataResult<>(employeeService.replaceEmployee(newEmployee, id));
    }

    @DeleteMapping("{id}")
    public Result deleteEmployee(@PathVariable int id) {
        employeeService.deleteById(id);
        return new SuccessResult();
    }
}
