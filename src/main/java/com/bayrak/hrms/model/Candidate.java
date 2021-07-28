package com.bayrak.hrms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="candidates")
public class Candidate extends User {

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="identity_number" , nullable = false,unique = true)
    private String identityNumber;

    @Column(name="birth_year", nullable = false)
    private int birthYear;

    @Builder
    public Candidate(String email, String password, String firstName, String lastName, String identityNumber, int birthYear) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityNumber = identityNumber;
        this.birthYear = birthYear;
    }
}

