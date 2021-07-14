package com.bayrak.hrms.business.abstracts;

import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.entity.concretes.JobAdvertisement;
import com.bayrak.hrms.entity.dto.jobAdvertisement.CloseJobAdvertisementByEmployerDto;
import com.bayrak.hrms.entity.dto.jobAdvertisement.JobAdvertiesementDto;
import com.bayrak.hrms.entity.dto.jobAdvertisement.postJobAdvertisementDto;

import java.util.List;

public interface JobAdvertiesementService {
    DataResult<List<JobAdvertisement>> getAll();
    DataResult<postJobAdvertisementDto> save(postJobAdvertisementDto entity);
    DataResult<List<JobAdvertiesementDto>> getAllByActiveAdvertisements();
    DataResult<List<JobAdvertiesementDto>> getAllBySortedByDateActiveAdvertisements();
    DataResult<List<JobAdvertiesementDto>> getAllByEmployer_CompanyName(String companyName);

    Result closeAdvertisementByEmployer(CloseJobAdvertisementByEmployerDto dto);
}

