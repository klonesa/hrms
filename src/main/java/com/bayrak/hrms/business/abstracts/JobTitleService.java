package com.bayrak.hrms.business.abstracts;

import com.bayrak.hrms.entity.concretes.JobTitle;

import java.util.List;

public interface JobTitleService {

    List<JobTitle> getAll();
    JobTitle get(int id);
    void save(JobTitle jobTitle);


}
