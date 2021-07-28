package com.bayrak.hrms.controller;


import com.bayrak.hrms.model.resume.JobTitle;
import com.bayrak.hrms.service.JobTitleService;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/job-titles")
public class JobTitlesController {
    private final JobTitleService jobTitleService;

    @GetMapping
    public DataResult<List<JobTitle>> getAll() {
        return new SuccessDataResult<>(jobTitleService.getAll()) ;
    }

    @GetMapping("{id}")
    public Result get(@PathVariable int id) {
        return new SuccessDataResult<>(jobTitleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid JobTitle jobTitle){
        return ResponseEntity.ok(new SuccessDataResult(jobTitleService.save(jobTitle))) ;
    }
}
