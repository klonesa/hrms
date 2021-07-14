package com.bayrak.hrms.entity.concretes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name="verification_codes")
@Inheritance(strategy = InheritanceType.JOINED)
public class VerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    @Column(name="code")
    private String code;
    @Column(name="is_verified")
    private boolean isVerified = false;
    @Column(name="verified_date")
    private Date verifiedDate;

    public VerificationCode(String code) {
        this.code = code;
    }
}
