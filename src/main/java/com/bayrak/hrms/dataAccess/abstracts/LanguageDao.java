package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.resume.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageDao extends JpaRepository<Language,Integer> {
    boolean existsByNameIgnoreCase(String name);

    Language findByNameIgnoreCase(String name);
}
