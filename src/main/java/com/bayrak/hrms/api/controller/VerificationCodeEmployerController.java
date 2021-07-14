package com.bayrak.hrms.api.controller;


import com.bayrak.hrms.business.abstracts.VerificationCodeEmployerService;
import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.entity.dto.IdDto;
import com.bayrak.hrms.entity.dto.VerificationCodeDto;
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
