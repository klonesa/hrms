package com.bayrak.hrms.business.concretes;


import com.bayrak.hrms.business.abstracts.JobTitleService;
import com.bayrak.hrms.dataAccess.abstracts.JobTitleDao;
import com.bayrak.hrms.entity.concretes.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleManager implements JobTitleService {

    private JobTitleDao jobTitleDao;

    @Autowired
    public JobTitleManager(JobTitleDao jobTitleDao) {
        this.jobTitleDao = jobTitleDao;
    }

    @Override
    public List<JobTitle> getAll() {
        return jobTitleDao.findAll();
    }

    @Override
    public JobTitle get(int id) {
        return jobTitleDao.getById(id);
    }
    @Override
    public void save(JobTitle jobTitle){
        jobTitleDao.save(jobTitle);
    }

}
