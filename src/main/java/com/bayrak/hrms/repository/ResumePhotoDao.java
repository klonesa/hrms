package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.resume.ResumePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResumePhotoDao extends JpaRepository<ResumePhoto, Integer> {}
