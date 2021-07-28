package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.resume.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTitleDao extends JpaRepository<JobTitle, Integer> {

    boolean existsByTitle(String title);

    JobTitle findByTitle(String title);

}

