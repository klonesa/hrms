package com.bayrak.hrms.entity.concretes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name="employers")
public class Employer extends User{

    @Column(name="company_name")
    private String companyName;

    @Column(name="web_adress")
    private String webAdress;

    @Column(name="phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "employer")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<VerificationCodeEmployer> verificationCodeEmployer= new ArrayList<>();

    public Employer(String email, String password, String companyName, String webAdress, String phoneNumber) {
        super(email, password);
        this.companyName = companyName;
        this.webAdress = webAdress;
        this.phoneNumber = phoneNumber;
    }
}