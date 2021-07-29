package com.bayrak.hrms.controller;


import com.bayrak.hrms.dto.IdDto;
import com.bayrak.hrms.dto.VerificationCodeDto;
import com.bayrak.hrms.service.EmployerService;
import com.bayrak.hrms.service.VerificationCodeEmployerService;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/verification/employer")
public class VerificationCodeEmployerController {

    private final VerificationCodeEmployerService verificationCodeEmployerService;
    private final EmployerService employerService;

    @GetMapping("{id}")
    public DataResult<String> getCode(@PathVariable int id) {
        return new SuccessDataResult<>(verificationCodeEmployerService.getLastCode(id));
    }

    @PostMapping()
    public DataResult<String> generateCode(@RequestBody IdDto id){

        return new SuccessDataResult<>(employerService.generateCode(id.getId()));
    }

    @PostMapping("/verify")
    public void verify(@RequestBody VerificationCodeDto verificationCodeDto){
         verificationCodeEmployerService.verify(verificationCodeDto.getId(), verificationCodeDto.getCode());
    }



}
