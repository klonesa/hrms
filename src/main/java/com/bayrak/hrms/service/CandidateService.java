package com.bayrak.hrms.service;

import com.bayrak.hrms.dto.CandidateDto;
import com.bayrak.hrms.dto.convertor.CandidateConverter;
import com.bayrak.hrms.exception.CandidateNotFoundException;
import com.bayrak.hrms.model.Candidate;
import com.bayrak.hrms.repository.CandidateDao;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateDao candidateDao;
    private final CandidateConverter candidateConverter;
    private final VerifyService<Candidate> candidateVerifyService;
    private final VerificationCodeCandidateService verificationCodeCandidateService;

    @Transactional
    public Result save(CandidateDto candidateDto) {
        final Candidate candidate = candidateConverter.DtoToEntity(candidateDto);
        candidateVerifyService.verify(candidate);
        candidateDao.save(candidate);
        verificationCodeCandidateService.generateCode(candidate.getId());
        return new SuccessDataResult<>(candidateDto,candidateDto.getFirstName() + " created succesfully");
    }

    public List<CandidateDto> findAll() {
        return candidateDao.findAll().stream().map(candidateConverter::EntityToDto)
                .collect(Collectors.toList());
    }

    public CandidateDto findByIdConverDto(int id) {
        return candidateConverter.EntityToDto(candidateDao.findById(id).orElseThrow(() -> {
            throw new CandidateNotFoundException(id);
        }));
    }

    protected Candidate findById(int id) {
        return candidateDao.findById(id).orElseThrow(() -> {
            throw new CandidateNotFoundException(id);
        });
    }

}
