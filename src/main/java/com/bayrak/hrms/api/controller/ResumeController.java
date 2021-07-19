package com.bayrak.hrms.api.controller;

import com.bayrak.hrms.business.abstracts.ResumeService;
import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.entity.concretes.resume.Resume;
import com.bayrak.hrms.entity.dto.resume.ResumeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("{id}")
    public DataResult getById(@PathVariable int id) {
        return resumeService.getById(id);
    }

    @GetMapping()
    public DataResult getAll() {
        return resumeService.getAll();
    }


    @PostMapping
    public Result add(@RequestBody ResumeRequestDto resume) {
        return resumeService.save(resume);
    }

}
