package com.bayrak.hrms.api.controller;


import com.bayrak.hrms.business.abstracts.EmployerService;
import com.bayrak.hrms.business.concretes.VerifyEmployerManager;
import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.core.utilities.results.SuccessDataResult;
import com.bayrak.hrms.entity.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {

    private final EmployerService employerService;
    private final VerifyEmployerManager verifyEmployerManager;

    @Autowired
    public EmployerController(EmployerService employerService, VerifyEmployerManager verifyEmployerManager) {
        this.employerService = employerService;
        this.verifyEmployerManager = verifyEmployerManager;
    }

    @GetMapping
    public DataResult<List<Employer>> getAll(){
        return new SuccessDataResult<>(employerService.findAll());
    }

    @GetMapping("{id}")
    public Result getById(@PathVariable int id){
        return new SuccessDataResult<>(employerService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Employer employer){

        Result result = verifyEmployerManager.verify(employer);

        if(result.isSuccess()){
            employerService.save(employer);
        }
        return result;
    }
}
