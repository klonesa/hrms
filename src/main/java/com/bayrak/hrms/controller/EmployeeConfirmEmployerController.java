package com.bayrak.hrms.controller;


import com.bayrak.hrms.dto.EmployeeConfirmEmployerDto;
import com.bayrak.hrms.service.EmployeeConfirmEmployerService;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/confirm-employer")
public class EmployeeConfirmEmployerController {

    private final EmployeeConfirmEmployerService employeeConfirmEmployerService;

    @PostMapping()
    public Result confirm(@RequestBody EmployeeConfirmEmployerDto employeeConfirmEmployerDto ) {
        return new SuccessDataResult<>(employeeConfirmEmployerService
                .confirmEmployer(employeeConfirmEmployerDto.getEmployeeId(),
                employeeConfirmEmployerDto.getEmployerId()));
   }
}
