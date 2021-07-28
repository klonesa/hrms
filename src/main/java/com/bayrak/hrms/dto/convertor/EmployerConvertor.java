package com.bayrak.hrms.dto.convertor;

import com.bayrak.hrms.dto.EmployerDto;
import com.bayrak.hrms.model.Employer;
import org.springframework.stereotype.Component;

@Component
public class EmployerConvertor implements Convertor<Employer, EmployerDto>{

    @Override
    public EmployerDto EntityToDto(Employer employer) {
        return EmployerDto.builder()
                .companyName(employer.getCompanyName())
                .email(employer.getEmail())
                .password(employer.getPassword())
                .phoneNumber(employer.getPhoneNumber())
                .webAdress(employer.getWebAdress())
                .build();
    }

    @Override
    public Employer DtoToEntity(EmployerDto employerDto) {
        return Employer.builder()
                .companyName(employerDto.getCompanyName())
                .email(employerDto.getEmail())
                .password(employerDto.getPassword())
                .phoneNumber(employerDto.getPhoneNumber())
                .webAdress(employerDto.getWebAdress())
                .build();
    }
}
