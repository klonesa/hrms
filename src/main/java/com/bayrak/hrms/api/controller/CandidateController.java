package com.bayrak.hrms.api.controller;


import com.bayrak.hrms.business.abstracts.CandidateService;
import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.core.utilities.results.SuccessDataResult;
import com.bayrak.hrms.entity.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(value = HttpStatus.OK)
    public DataResult<List<Candidate>> getAll(){
        return new SuccessDataResult<>(candidateService.findAll());
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Result getById(@PathVariable int id){
        return new SuccessDataResult<>(candidateService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result add(@RequestBody Candidate candidate){
            return candidateService.save(candidate);
    }
}
