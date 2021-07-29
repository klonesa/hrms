package com.bayrak.hrms.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
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
public class EmployeeDto {

    @JsonProperty(access = READ_ONLY)
    private int id;

    @Email
    private String email;

    @Size(min = 6, max = 50)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank(message="First name cannot be blank")
    private String firstName;

    @NotBlank(message="Last name cannot be blank")
    private String lastName;

}
