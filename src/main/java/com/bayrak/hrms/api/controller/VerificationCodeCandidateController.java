package com.bayrak.hrms.api.controller;


import com.bayrak.hrms.business.abstracts.VerificationCodeCandidateService;
import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.entity.dto.IdDto;
import com.bayrak.hrms.entity.dto.VerificationCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate-verification")
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
