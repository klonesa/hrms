package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.resume.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingLanguageDao extends JpaRepository<ProgrammingLanguage, Integer> {
    boolean existsByNameIgnoreCase(String name);

    ProgrammingLanguage findByNameIgnoreCase(String name);
}
