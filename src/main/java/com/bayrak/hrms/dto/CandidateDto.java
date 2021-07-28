package com.bayrak.hrms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder
public class CandidateDto {

    @JsonProperty(access = READ_ONLY)
    private int id;

    @Email
    private String email;

    @Size(min = 6, max = 50)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @NotBlank(message="First name cannot be blank")
    private String firstName;

    @NotBlank(message="Last name cannot be blank")
    private String lastName;

    @NotBlank(message="Identity cannot be blank")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String identityNumber;

    @Range(min = 1900,max = 2021 )
    @Column(name="birth_year")
    private int birthYear;


}
