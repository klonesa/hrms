package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTitleDao extends JpaRepository<JobTitle,Integer > {

}

