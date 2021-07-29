package com.bayrak.hrms.controller;


import com.bayrak.hrms.dto.jobAdvertisement.CloseJobAdvertisementByEmployerDto;
import com.bayrak.hrms.dto.jobAdvertisement.JobAdvertiesementDto;
import com.bayrak.hrms.dto.jobAdvertisement.postJobAdvertisementDto;
import com.bayrak.hrms.model.JobAdvertisement;
import com.bayrak.hrms.service.JobAdvertiesementService;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import com.bayrak.hrms.utils.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementController {

    private final JobAdvertiesementService jobAdvertiesementService;

    @PostMapping()
    public DataResult<JobAdvertiesementDto> add(@RequestBody postJobAdvertisementDto data) {
        return new SuccessDataResult<>(jobAdvertiesementService.save(data));
    }

    @GetMapping
    public DataResult<List<JobAdvertisement>> getAll() {
        return new SuccessDataResult<>(jobAdvertiesementService.getAll());
    }

    @GetMapping("format")
    public DataResult<List<JobAdvertiesementDto>> getAllFiltered() {
        return new SuccessDataResult<>(jobAdvertiesementService.getAllByActiveAdvertisements());
    }

    @GetMapping("getAllBySortedByCreateDate")
    public DataResult<List<JobAdvertiesementDto>> getAllBySortedByCreateDate() {
        return new SuccessDataResult<>(jobAdvertiesementService.getAllBySortedByDateActiveAdvertisements());
    }

    @GetMapping("/getByCompanyName")
    public DataResult<List<JobAdvertiesementDto>>
    getAllByEmployer_CompanyName(@RequestParam String companyName) {
        return new SuccessDataResult<>(jobAdvertiesementService.getAllByEmployerCompanyName(companyName));
    }

    @PostMapping("/closeAdvertisement")
    public Result closeAdvertisemenet(@RequestBody CloseJobAdvertisementByEmployerDto dto) {
        jobAdvertiesementService.closeAdvertisementByEmployer(dto);
        return new SuccessResult("Advertisement closed successfully");
    }
}
