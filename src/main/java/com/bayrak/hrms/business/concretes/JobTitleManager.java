package com.bayrak.hrms.business.concretes;


import com.bayrak.hrms.business.abstracts.JobTitleService;
import com.bayrak.hrms.repository.JobTitleDao;
import com.bayrak.hrms.entity.concretes.resume.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobTitleManager implements JobTitleService {

    private final JobTitleDao jobTitleDao;

    @Autowired
    public JobTitleManager(JobTitleDao jobTitleDao) {
        this.jobTitleDao = jobTitleDao;
    }

    @Override
    public List<JobTitle> getAll() {
        return jobTitleDao.findAll();
    }

    @Override
    public Optional<JobTitle> get(int id) {
        return jobTitleDao.findById(id);
    }

    @Override
    public void save(JobTitle jobTitle){
        jobTitleDao.save(jobTitle);
    }

}
