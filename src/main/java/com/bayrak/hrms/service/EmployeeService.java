package com.bayrak.hrms.service;

import com.bayrak.hrms.dto.EmployeeDto;
import com.bayrak.hrms.dto.convertor.EmployeeConvertor;
import com.bayrak.hrms.exception.EmployeeNotFoundException;
import com.bayrak.hrms.repository.EmployeeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeDao employeeDao;
    private final EmployeeConvertor employeeConvertor;

    public EmployeeDto save(EmployeeDto employeeDto) {
        return employeeConvertor
                .EntityToDto(employeeDao.save(employeeConvertor.DtoToEntity(employeeDto)));
    }

    public List<EmployeeDto> findAll() {
        return employeeDao.findAll().stream().map(employeeConvertor::EntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDto> findByIdOptional(int id) {
        return employeeDao.findById(id).map(employeeConvertor::EntityToDto);
        }

    public EmployeeDto findById(int id) {
        return employeeConvertor.EntityToDto(employeeDao.findById(id)
                .orElseThrow(()-> {
            throw new EmployeeNotFoundException(id);
        }));
    }

    public EmployeeDto replaceEmployee(EmployeeDto newEmployeeDto, @PathVariable int id) {
        return findByIdOptional(id)
                .map(employee -> {
                    employee.setEmail(newEmployeeDto.getEmail());
                    employee.setFirstName(newEmployeeDto.getFirstName());
                    employee.setLastName(newEmployeeDto.getLastName());
                    employee.setPassword(newEmployeeDto.getPassword());
                    return save(employee);
                })
                .orElseGet(() -> {
                    newEmployeeDto.setId(id);
                    return save(newEmployeeDto);
                });
    }

    public void deleteById(int id) {
        employeeDao.findById(id).ifPresentOrElse(
                employee -> employeeDao.deleteById(id), () -> {
                    throw new EmployeeNotFoundException(id);
                }
        );
    }
}
