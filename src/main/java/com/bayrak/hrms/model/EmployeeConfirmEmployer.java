package com.bayrak.hrms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee_confirm_employers")
public class EmployeeConfirmEmployer extends EmployeeConfirm {

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    public EmployeeConfirmEmployer(Employee employee, Employer employer) {
        super(employee, true,new Date());
        this.employer = employer;
    }

}
