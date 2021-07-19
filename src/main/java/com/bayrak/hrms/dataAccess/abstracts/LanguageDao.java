package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.resume.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageDao extends JpaRepository<Language,Integer> {
    boolean existsByName(String name);

    Language findByName(String name);
}
