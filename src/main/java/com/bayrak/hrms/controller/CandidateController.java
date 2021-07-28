package com.bayrak.hrms.controller;


import com.bayrak.hrms.dto.CandidateDto;
import com.bayrak.hrms.service.CandidateService;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public DataResult<List<CandidateDto>> getAll(){
        return new SuccessDataResult<>(candidateService.findAll());
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Result getById(@PathVariable int id){
        return new SuccessDataResult<>(candidateService.findByIdConverDto(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid CandidateDto candidateDto){
        return new ResponseEntity<>(candidateService.save(candidateDto), HttpStatus.CREATED);
    }
}
