package com.bayrak.hrms.api.controller;


import com.bayrak.hrms.business.abstracts.JobTitleService;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.core.utilities.results.SuccessDataResult;
import com.bayrak.hrms.core.utilities.results.SuccessResult;
import com.bayrak.hrms.entity.concretes.resume.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-titles")
public class JobTitlesController {
    private final JobTitleService jobTitleService;

    @Autowired
    public JobTitlesController(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }

    @GetMapping
    public List<JobTitle> getAll() {
        return jobTitleService.getAll();
    }

    @GetMapping("{id}")
    public Result get(@PathVariable int id) {
        return new SuccessDataResult<Optional<JobTitle>> (jobTitleService.get(id));
    }

    @PostMapping
    public Result add(@RequestBody JobTitle jobTitle){

        jobTitleService.save(jobTitle);

        return new SuccessResult() ;
    }

}
