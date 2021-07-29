package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.resume.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobTitleDao extends JpaRepository<JobTitle, Integer> {
    @Query(value = "select t.title from JobTitle t")
    List<String> findAllString();
}

