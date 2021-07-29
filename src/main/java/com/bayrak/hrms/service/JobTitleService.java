package com.bayrak.hrms.service;


import com.bayrak.hrms.exception.JobTitleNotFoundException;
import com.bayrak.hrms.model.resume.JobTitle;
import com.bayrak.hrms.repository.JobTitleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobTitleService {

    private final JobTitleDao jobTitleDao;

    public List<JobTitle> getAll() {
        return jobTitleDao.findAll();
    }

    public JobTitle findById(int id) {
        return jobTitleDao.findById(id).orElseThrow(() -> {
            throw new JobTitleNotFoundException(id);
        });
    }

    public List<String> findAllString() {
        return jobTitleDao.findAllString();
    }

    public JobTitle save(JobTitle jobTitle){
        return jobTitleDao.save(jobTitle);
    }

}
