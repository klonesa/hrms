package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {
}
