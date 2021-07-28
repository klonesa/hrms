package com.bayrak.hrms.dto.convertor;

import com.bayrak.hrms.dto.CandidateDto;
import com.bayrak.hrms.model.Candidate;
import org.springframework.stereotype.Component;

@Component
public class CandidateConverter implements Convertor<Candidate, CandidateDto> {


    @Override
    public CandidateDto EntityToDto(Candidate candidate) {

        return CandidateDto.builder()
                .id(candidate.getId())
                .email(candidate.getEmail())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .identityNumber(candidate.getIdentityNumber())
                .birthYear(candidate.getBirthYear())
                .build();
    }

    @Override
    public Candidate DtoToEntity(CandidateDto candidateDto) {
        return Candidate.builder()
                .email(candidateDto.getEmail())
                .password(candidateDto.getPassword())
                .firstName(candidateDto.getFirstName())
                .lastName(candidateDto.getLastName())
                .identityNumber(candidateDto.getIdentityNumber())
                .birthYear(candidateDto.getBirthYear())
                .build();
    }
}
