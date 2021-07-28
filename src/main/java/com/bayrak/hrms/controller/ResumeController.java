package com.bayrak.hrms.controller;

import com.bayrak.hrms.dto.resume.ResumeDto;
import com.bayrak.hrms.dto.resume.ResumePhotoDto;
import com.bayrak.hrms.service.ResumePhotoService;
import com.bayrak.hrms.service.ResumeService;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final ResumePhotoService resumePhotoService;

    @GetMapping("{id}")
    public DataResult getById(@PathVariable int id) {
        return new SuccessDataResult(resumeService.getById(id));
    }

    @GetMapping()
    public DataResult getAll() {
        return new SuccessDataResult(resumeService.getAll());
    }


    @PostMapping
    public Result add(@RequestBody ResumeDto resume) {
        return new SuccessDataResult<>(resumeService.save(resume));
    }

    @PostMapping("/addPhoto")
    public DataResult addPhoto(@RequestBody ResumePhotoDto dto) {
            return new SuccessDataResult(resumePhotoService
                    .addPhoto(dto.getResumeId(), dto.getPhotoUri()));

    }

    @PutMapping("/updatePhoto")
    public DataResult updatePhoto(@RequestBody ResumePhotoDto dto) {
        return new SuccessDataResult(resumePhotoService
                .updatePhoto(dto.getResumeId(), dto.getPhotoUri()));
    }
}
