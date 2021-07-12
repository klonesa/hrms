package com.bayrak.hrms.api.controller;


import com.bayrak.hrms.business.abstracts.CandidateService;
import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.ErrorResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.core.utilities.results.SuccessDataResult;
import com.bayrak.hrms.entity.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public DataResult<List<Candidate>> getAll(){
        return new SuccessDataResult<>(candidateService.findAll());
    }

    @GetMapping("{id}")
    public Result getById(@PathVariable int id){
        return new SuccessDataResult<>(candidateService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Candidate candidate){
        try {
            return candidateService.save(candidate) ;
        } catch (Exception e) {
            return new ErrorResult("Identity number or email already exist - " + e.getMessage());
        }
    }
}
