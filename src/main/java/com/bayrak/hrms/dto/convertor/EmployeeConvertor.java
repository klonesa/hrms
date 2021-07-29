package com.bayrak.hrms.dto.convertor;

import com.bayrak.hrms.dto.EmployeeDto;
import com.bayrak.hrms.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConvertor implements Convertor<Employee, EmployeeDto> {

    @Override
    public EmployeeDto EntityToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .build();
    }

    @Override
    public Employee DtoToEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .email(employeeDto.getEmail())
                .password(employeeDto.getPassword())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .build();
    }
}
