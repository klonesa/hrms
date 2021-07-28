package com.bayrak.hrms.service;

import com.bayrak.hrms.dto.jobAdvertisement.CloseJobAdvertisementByEmployerDto;
import com.bayrak.hrms.dto.jobAdvertisement.JobAdvertiesementDto;
import com.bayrak.hrms.dto.jobAdvertisement.postJobAdvertisementDto;
import com.bayrak.hrms.exception.AuthorizationException;
import com.bayrak.hrms.exception.EmployerNotFoundException;
import com.bayrak.hrms.exception.JobAdvertisementAlreadyClosedException;
import com.bayrak.hrms.exception.JobAdvertisementNotFoundException;
import com.bayrak.hrms.model.JobAdvertisement;
import com.bayrak.hrms.repository.JobAdvertiesementDao;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import com.bayrak.hrms.utils.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobAdvertiesementService {

    private final JobAdvertiesementDao jobAdvertiesementDao;
    private final JobTitleService jobTitleService;
    private final CityService cityService;
    private final EmployerService employerService;

    public Result closeAdvertisementByEmployer(CloseJobAdvertisementByEmployerDto dto) {

        if (!employerService.existsById(dto.getEmployerId())){
            throw new EmployerNotFoundException(dto.getEmployerId());
        }

        jobAdvertiesementDao.findById(dto.getJobAdvertisementId())
                .ifPresentOrElse((i) -> {
                    if(dto.getEmployerId()!=i.getEmployer().getId()){
                        throw new AuthorizationException("Authorization error");
                    }
                    if(i.isActive()){
                        throw new JobAdvertisementAlreadyClosedException("Aldvertisement already closed!");
                    }
                    i.setActive(false);
                    jobAdvertiesementDao.save(i);

                },()-> {
                    throw new JobAdvertisementNotFoundException("Job advertisement not found ," +
                            " id : " + dto.getJobAdvertisementId());
                });

        return new SuccessResult("Job advertisement closed");
    }

    public JobAdvertisement save(postJobAdvertisementDto dto) {
        return jobAdvertiesementDao.save(new JobAdvertisement(
                jobTitleService.findById(dto.getJobTitleId()),
                dto.getDescription(),
                cityService.findById(dto.getCityId()),
                dto.getMinSalary(),
                dto.getMaxSalary(),
                dto.getOpenPositionNumber(),
                dto.getCloseDate(),
                employerService.findById(dto.getEmployerId())));
    }

    public List<JobAdvertisement> getAll() {
        return jobAdvertiesementDao.findAll();
    }

    public List<JobAdvertiesementDto> getAllByActiveAdvertisements() {
        return jobAdvertiesementDao.getAllByActiveAdvertisements();
    }

    public List<JobAdvertiesementDto> getAllBySortedByDateActiveAdvertisements() {
        return jobAdvertiesementDao.getAllBySortedByDateActiveAdvertisements();
    }

    public List<JobAdvertiesementDto> getAllByEmployerCompanyName(String companyName) {
        return jobAdvertiesementDao.getAllByEmployer_CompanyName(companyName);
    }

}
