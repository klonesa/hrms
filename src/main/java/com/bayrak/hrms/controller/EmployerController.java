package com.bayrak.hrms.controller;


import com.bayrak.hrms.dto.EmployerDto;
import com.bayrak.hrms.dto.convertor.EmployerConvertor;
import com.bayrak.hrms.service.EmployerService;
import com.bayrak.hrms.service.VerifyEmployerService;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import com.bayrak.hrms.utils.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employers")
public class EmployerController {

    private final EmployerService employerService;
    private final VerifyEmployerService verifyEmployerService;
    private final EmployerConvertor employerConvertor;

    @GetMapping
    public DataResult<List<EmployerDto>> getAll(){
        return new SuccessDataResult<>(employerService.findAll());
    }

    @GetMapping("{id}")
    public Result getById(@PathVariable int id){
        return new SuccessDataResult<>(employerService.findByIdConvertDto(id));
    }

    @PostMapping
    public Result add(@RequestBody @Valid EmployerDto employerDto){
        verifyEmployerService.verify(employerConvertor.DtoToEntity(employerDto));
        employerService.save(employerDto);
        return new SuccessResult();
    }
}
