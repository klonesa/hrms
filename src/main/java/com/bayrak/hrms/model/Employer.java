package com.bayrak.hrms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@EqualsAndHashCode(callSuper = true)
@Data @Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employers")
public class Employer extends User{

    @Column(name="company_name")
    private String companyName;

    @Column(name="web_adress")
    private String webAdress;

    @Column(name="phone_number")
    private String phoneNumber;

    @Builder
    public Employer(String email, String password, String companyName, String webAdress, String phoneNumber) {
        super(email, password);
        this.companyName = companyName;
        this.webAdress = webAdress;
        this.phoneNumber = phoneNumber;
    }
}
