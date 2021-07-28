package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.resume.ResumeLanguageLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeLanguagLevelDao extends JpaRepository<ResumeLanguageLevel,Integer> {

    ResumeLanguageLevel findByLanguage(String languageName);
    boolean existsByLanguage(String languageName);
}
