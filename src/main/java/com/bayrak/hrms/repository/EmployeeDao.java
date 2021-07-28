package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {
}
