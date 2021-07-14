package com.bayrak.hrms.api.controller;


import com.bayrak.hrms.business.abstracts.JobAdvertiesementService;
import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.entity.concretes.JobAdvertisement;
import com.bayrak.hrms.entity.dto.jobAdvertisement.CloseJobAdvertisementByEmployerDto;
import com.bayrak.hrms.entity.dto.jobAdvertisement.JobAdvertiesementDto;
import com.bayrak.hrms.entity.dto.jobAdvertisement.postJobAdvertisementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementController {

    private final JobAdvertiesementService jobAdvertiesementService;

    @Autowired
    public JobAdvertisementController(JobAdvertiesementService jobAdvertiesementService) {
        this.jobAdvertiesementService = jobAdvertiesementService;
    }

    @PostMapping()
    public DataResult<postJobAdvertisementDto> add(@RequestBody postJobAdvertisementDto data) {
        return jobAdvertiesementService.save(data);
    }


    @GetMapping
    public DataResult<List<JobAdvertisement>> getAll() {
        return jobAdvertiesementService.getAll();
    }

    @GetMapping("format")
    public DataResult<List<JobAdvertiesementDto>> getAllFiltered() {
        return jobAdvertiesementService.getAllByActiveAdvertisements();

    }

    @GetMapping("getAllBySortedByCreateDate")
    public DataResult<List<JobAdvertiesementDto>> getAllBySortedByCreateDate() {
        return jobAdvertiesementService.getAllBySortedByDateActiveAdvertisements();

    }

    @GetMapping("/getByCompanyName")
    public DataResult<List<JobAdvertiesementDto>>
    getAllByEmployer_CompanyName(@RequestParam String companyName) {
        return jobAdvertiesementService.getAllByEmployer_CompanyName(companyName);
    }

    @PostMapping("/closeAdvertisement")
    public Result closeAdvertisemenet(@RequestBody CloseJobAdvertisementByEmployerDto dto) {
        return jobAdvertiesementService.closeAdvertisementByEmployer(dto);
    }
}
