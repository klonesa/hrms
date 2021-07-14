package com.bayrak.hrms.entity.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name="verification_code_employers")
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCodeEmployer extends VerificationCode {

    @OneToOne
    @JoinColumn(name="employer_id")
    private Employer employer;

    public VerificationCodeEmployer(String code, Employer employer) {
        super(code);
        this.employer = employer;
    }

}
