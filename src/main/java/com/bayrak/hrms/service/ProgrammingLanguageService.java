package com.bayrak.hrms.service;

import com.bayrak.hrms.model.resume.ProgrammingLanguage;
import com.bayrak.hrms.repository.ProgrammingLanguageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgrammingLanguageService {

    private final ProgrammingLanguageDao programmingLanguageDao;

    protected boolean existsByNameIgnoreCase(String name){
        return programmingLanguageDao.existsByNameIgnoreCase(name);
    }

    protected ProgrammingLanguage findByNameIgnoreCase(String name) {
        return programmingLanguageDao.findByNameIgnoreCase(name);
    }

}
