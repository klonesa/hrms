package com.bayrak.hrms.api.controller;

import com.bayrak.hrms.business.abstracts.ResumePhotoService;
import com.bayrak.hrms.business.abstracts.ResumeService;
import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.ErrorDataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.entity.dto.resume.ResumeDto;
import com.bayrak.hrms.entity.dto.resume.ResumePhotoDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final ResumePhotoService resumePhotoService;

    @GetMapping("{id}")
    public DataResult getById(@PathVariable int id) {
        return resumeService.getById(id);
    }

    @GetMapping()
    public DataResult getAll() {
        return resumeService.getAll();
    }


    @PostMapping
    public Result add(@RequestBody ResumeDto resume) {
        return resumeService.save(resume);
    }


    @PostMapping("/addPhoto")
    public DataResult addPhoto(@RequestBody ResumePhotoDto dto) {
        try {
            return resumePhotoService.addPhoto(dto.getResumeId(), dto.getPhotoUri());
        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorDataResult(e.getMessage());
        }
    }


    @PutMapping("/updatePhoto")
    public DataResult updatePhoto(@RequestBody ResumePhotoDto dto) {
        try {
            return resumePhotoService.updatePhoto(dto.getResumeId(), dto.getPhotoUri());
        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorDataResult(e.getMessage());
        }
    }


}
