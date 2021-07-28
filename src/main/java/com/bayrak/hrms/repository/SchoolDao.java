package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.resume.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolDao extends JpaRepository<School, Integer> {

}
