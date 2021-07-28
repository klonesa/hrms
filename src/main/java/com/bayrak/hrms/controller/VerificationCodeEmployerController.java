package com.bayrak.hrms.controller;


import com.bayrak.hrms.dto.IdDto;
import com.bayrak.hrms.dto.VerificationCodeDto;
import com.bayrak.hrms.service.VerificationCodeEmployerService;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verification/employer")
public class VerificationCodeEmployerController {

    private final VerificationCodeEmployerService verificationCodeEmployerService;

    @Autowired
    public VerificationCodeEmployerController(VerificationCodeEmployerService verificationCodeEmployerService) {
        this.verificationCodeEmployerService = verificationCodeEmployerService;
    }

    @GetMapping("{id}")
    public DataResult<String> getCode(@PathVariable int id) {

        return verificationCodeEmployerService.getLastCode(id);
    }

    @PostMapping()
    public DataResult<String> generateCode(@RequestBody IdDto id){

        return verificationCodeEmployerService.generateCode(id.getId());
    }

    @PostMapping("/verify")
    public Result verify(@RequestBody VerificationCodeDto verificationCodeDto){
        return verificationCodeEmployerService.verify(verificationCodeDto.getId(), verificationCodeDto.getCode());
    }



}
