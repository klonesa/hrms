package com.bayrak.hrms.service;

import com.bayrak.hrms.dto.EmployerDto;
import com.bayrak.hrms.dto.convertor.EmployerConvertor;
import com.bayrak.hrms.exception.EmployerNotFoundException;
import com.bayrak.hrms.model.Employer;
import com.bayrak.hrms.repository.EmployerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private final EmployerDao employerDao;
    private final VerificationCodeEmployerService verificationCodeEmployerService;
    private final VerifyEmployerService verifyEmployerService;
    private final EmployerConvertor employerConvertor;

    public void save(EmployerDto employerDto) {
        final Employer employer = employerConvertor.DtoToEntity(employerDto);
        verifyEmployerService.verify(employer);
        employerDao.save(employer);
        verificationCodeEmployerService.generateCode(employer.getId());
    }

    public List<EmployerDto> findAll() {
      return employerDao.findAll().stream().map(employerConvertor::EntityToDto)
              .collect(Collectors.toList());
    }

    public Employer findById(int id) {
        return employerDao.findById(id).orElseThrow(()-> {
            throw new EmployerNotFoundException(id);
        });
    }

    public boolean existsById(int employerId) {
        return employerDao.existsById(employerId);
    }
}
