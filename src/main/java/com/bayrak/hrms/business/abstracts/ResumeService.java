package com.bayrak.hrms.business.abstracts;


import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.Result;
import com.bayrak.hrms.entity.dto.resume.ResumeDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResumeService {
    DataResult<ResumeDto> getById(int id);
    DataResult<List<ResumeDto>> getAll();

    @Transactional
    Result save(ResumeDto resumeRequestDto);
}
