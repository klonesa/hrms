package com.bayrak.hrms.business.abstracts;

import com.bayrak.hrms.entity.concretes.resume.JobTitle;

import java.util.List;
import java.util.Optional;

public interface JobTitleService {

    void save(JobTitle jobTitle);
    List<JobTitle> getAll();
    Optional<JobTitle> get(int id);


}
