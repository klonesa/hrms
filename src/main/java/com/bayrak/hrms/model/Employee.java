package com.bayrak.hrms.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
public class Employee extends User {

    @NotBlank(message="First name cannot be blank")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message="Last name cannot be blank")
    @Column(name="last_name")
    private String lastName;

    @Builder
    public Employee(String email, String password, String firstName, String lastName) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

