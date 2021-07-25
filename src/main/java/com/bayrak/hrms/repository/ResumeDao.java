package com.bayrak.hrms.repository;

import com.bayrak.hrms.entity.concretes.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeDao extends JpaRepository<Resume, Integer> {
}
