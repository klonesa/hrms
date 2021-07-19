package com.bayrak.hrms.entity.concretes;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="employee_confirms")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class EmployeeConfirm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name="is_confirmed")
    private boolean isConfirmed=false;


    @Column(name = "confirm_date")
    private Date confirmDate;

    public EmployeeConfirm(Employee employee, boolean isConfirmed, Date confirmDate) {
        this.employee = employee;
        this.isConfirmed = isConfirmed;
        this.confirmDate = confirmDate;
    }

}
