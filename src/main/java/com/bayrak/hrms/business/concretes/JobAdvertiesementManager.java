package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.JobAdvertiesementService;
import com.bayrak.hrms.core.utilities.results.*;
import com.bayrak.hrms.dataAccess.abstracts.CityDao;
import com.bayrak.hrms.dataAccess.abstracts.EmployerDao;
import com.bayrak.hrms.dataAccess.abstracts.JobAdvertiesementDao;
import com.bayrak.hrms.dataAccess.abstracts.JobTitleDao;
import com.bayrak.hrms.entity.concretes.City;
import com.bayrak.hrms.entity.concretes.Employer;
import com.bayrak.hrms.entity.concretes.JobAdvertisement;
import com.bayrak.hrms.entity.concretes.resume.JobTitle;
import com.bayrak.hrms.entity.dto.jobAdvertisement.CloseJobAdvertisementByEmployerDto;
import com.bayrak.hrms.entity.dto.jobAdvertisement.JobAdvertiesementDto;
import com.bayrak.hrms.entity.dto.jobAdvertisement.postJobAdvertisementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobAdvertiesementManager implements JobAdvertiesementService {

    private final JobAdvertiesementDao jobAdvertiesementDao;
    private final JobTitleDao jobTitleDao;
    private final CityDao cityDao;
    private final EmployerDao employerDao;

    @Autowired
    public JobAdvertiesementManager(JobAdvertiesementDao jobAdvertiesementDao, JobTitleDao jobTitleDao, CityDao cityDao, EmployerDao employerDao) {
        this.jobAdvertiesementDao = jobAdvertiesementDao;
        this.jobTitleDao = jobTitleDao;
        this.cityDao = cityDao;
        this.employerDao = employerDao;
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAll() {
        return new SuccessDataResult<>(jobAdvertiesementDao.findAll());
    }

    @Override
    public DataResult<postJobAdvertisementDto> save(postJobAdvertisementDto entity) {
        int cityId = entity.getCityId();
        int employerId = entity.getEmployerId();
        int jobTitleId = entity.getJobTitleId();

        if (jobTitleDao.findById(jobTitleId).isEmpty()) {
            return new ErrorDataResult<>(null, "Job title not found");
        }
        if (employerDao.findById(employerId).isEmpty()) {
            return new ErrorDataResult<>(null, "Employer not found");
        }
        if (cityDao.findById(cityId).isEmpty()) {
            return new ErrorDataResult<>(null, "City not found");
        }

        Employer employer = employerDao.findById(employerId).get();
        City city = cityDao.findById(cityId).get();
        JobTitle jobTitle = jobTitleDao.findById(jobTitleId).get();

        JobAdvertisement jobAdvertisement = new JobAdvertisement(
                jobTitle, entity.getDescription(), city, entity.getMinSalary(),
                entity.getMaxSalary(), entity.getOpenPositionNumber(),
                entity.getCloseDate(), employer
        );
        jobAdvertiesementDao.save(jobAdvertisement);
        return new SuccessDataResult<>(entity,"Job advertisement created succsefully");
    }

    @Override
    public DataResult<List<JobAdvertiesementDto>> getAllByActiveAdvertisements() {
        return new SuccessDataResult<>(jobAdvertiesementDao.getAllByActiveAdvertisements());
    }

    @Override
    public DataResult<List<JobAdvertiesementDto>> getAllBySortedByDateActiveAdvertisements() {
        return new SuccessDataResult<>(jobAdvertiesementDao.getAllBySortedByDateActiveAdvertisements());
    }

    @Override
    public DataResult<List<JobAdvertiesementDto>> getAllByEmployer_CompanyName(String companyName) {
        return new SuccessDataResult<>(jobAdvertiesementDao.getAllByEmployer_CompanyName(companyName));
    }

    @Override
    public Result closeAdvertisementByEmployer(CloseJobAdvertisementByEmployerDto dto) {
        if (!employerDao.existsById(dto.getEmployerId())){
            return new ErrorResult("Employer Not found");
        }
         Optional<JobAdvertisement>  jobAdvertisement = jobAdvertiesementDao.findById(dto.getJobAdvertisementId());

        if (jobAdvertisement.isEmpty()) {
            return new ErrorResult("Job advertisement not found");
        }

        if (!jobAdvertisement.get().isActive()) {
            return new ErrorResult("Aldvertisement already closed!");
        }

        if(dto.getEmployerId()!=jobAdvertisement.get().getEmployer().getId()){
            return new ErrorResult("Not authorized to do this");
        }

        jobAdvertisement.get().setActive(false);
        jobAdvertiesementDao.save(jobAdvertisement.get());
        return new SuccessResult("Job advertisement closed");
    }
}
