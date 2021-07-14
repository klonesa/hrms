package com.bayrak.hrms.api.controller;


import com.bayrak.hrms.business.abstracts.EmployeeConfirmEmployerService;
import com.bayrak.hrms.entity.dto.EmployeeConfirmEmployerDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirm-employer")
public class EmployeeConfirmEmployerController {

    private final EmployeeConfirmEmployerService employeeConfirmEmployerService;

    public EmployeeConfirmEmployerController(EmployeeConfirmEmployerService employeeConfirmEmployerService) {
        this.employeeConfirmEmployerService = employeeConfirmEmployerService;
    }

    @PostMapping()
    public void confirm(@RequestBody EmployeeConfirmEmployerDto employeeConfirmEmployerDto ) {
        employeeConfirmEmployerService.confirmEmployer(employeeConfirmEmployerDto.getEmployeeId(),
                employeeConfirmEmployerDto.getEmployerId());
    }


}
