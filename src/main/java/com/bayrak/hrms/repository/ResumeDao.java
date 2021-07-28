package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeDao extends JpaRepository<Resume, Integer> {
}
