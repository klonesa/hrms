package com.bayrak.hrms.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="verification_code_candidates")
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCodeCandidate extends VerificationCode {

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    public VerificationCodeCandidate(String code,Candidate candidate) {
        super(code);
        this.candidate = candidate;
    }
}
