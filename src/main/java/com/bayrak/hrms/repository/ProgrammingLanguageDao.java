package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.resume.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingLanguageDao extends JpaRepository<ProgrammingLanguage, Integer> {
    boolean existsByNameIgnoreCase(String name);
    ProgrammingLanguage findByNameIgnoreCase(String name);
}
