package com.bayrak.hrms.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="candidates")
public class Candidate extends User {

    @NotBlank(message="First name cannot be blank")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message="Last name cannot be blank")
    @Column(name="last_name")
    private String lastName;

    @NotBlank(message="Identity cannot be blank")
    @Column(name="identity_number")
    private String identityNumber;

    @NotBlank(message="Birth year cannot be blank")
    @Column(name="birth_year")
    private int birthYear;

    public Candidate(String email, String password, String firstName, String lastName, String identityNumber, int birthYear) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityNumber = identityNumber;
        this.birthYear = birthYear;
    }
}

