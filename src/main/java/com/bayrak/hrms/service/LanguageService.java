package com.bayrak.hrms.service;

import com.bayrak.hrms.model.resume.Language;
import com.bayrak.hrms.repository.LanguageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageDao languageDao;

    protected boolean existsByNameIgnoreCase(String name) {
        return languageDao.existsByNameIgnoreCase(name);
    }

    protected Language findByNameIgnoreCase(String name) {
        return languageDao.findByNameIgnoreCase(name);
    }
}
