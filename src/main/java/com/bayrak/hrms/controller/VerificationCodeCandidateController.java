package com.bayrak.hrms.controller;


import com.bayrak.hrms.dto.IdDto;
import com.bayrak.hrms.dto.VerificationCodeDto;
import com.bayrak.hrms.service.VerificationCodeCandidateService;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verification/candidate")
public class VerificationCodeCandidateController {

    private final VerificationCodeCandidateService verificationCodeCandidateService;

    @Autowired
    public VerificationCodeCandidateController(VerificationCodeCandidateService verificationCodeCandidateService) {
        this.verificationCodeCandidateService = verificationCodeCandidateService;
    }

    @GetMapping("{id}")
    public DataResult<String> getCode(@PathVariable int id) {

        return verificationCodeCandidateService.getLastCode(id);
    }

    @PostMapping()
    public DataResult<String> generateCode(@RequestBody IdDto id){

        return verificationCodeCandidateService.generateCode(id.getId());
    }

    @PostMapping("/verify")
    public Result verify(@RequestBody VerificationCodeDto verificationCodeDto){
        return verificationCodeCandidateService.verify(verificationCodeDto.getId(), verificationCodeDto.getCode());
    }


}
