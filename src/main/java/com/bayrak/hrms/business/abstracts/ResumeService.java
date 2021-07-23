package com.bayrak.hrms.business.abstracts;


import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.entity.concretes.resume.Resume;
import com.bayrak.hrms.entity.dto.resume.ResumeRequestDto;
import com.bayrak.hrms.entity.dto.resume.ResumeResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResumeService {
    DataResult<ResumeResponseDto> getById(int id);
    DataResult<List<Resume>> getAll();

    @Transactional
    Result save(ResumeRequestDto resumeRequestDto);
}
