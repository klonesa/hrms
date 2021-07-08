package com.bayrak.hrms.api.controller;


import com.bayrak.hrms.business.abstracts.JobTitleService;
import com.bayrak.hrms.entity.concretes.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/job-titles")
public class JobTitlesController {
    private JobTitleService jobTitleService;

    @Autowired
    public JobTitlesController(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }

    @GetMapping
    public List<JobTitle> getAll() {
        return jobTitleService.getAll();
    }

}
