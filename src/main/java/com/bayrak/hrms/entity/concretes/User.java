package com.bayrak.hrms.entity.concretes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity(name="users")
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank(message="Email cannot be blank")
    @Column(name="email")
    private String email;

    @NotBlank(message="password cannot be blank")
    @Column(name="password")
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
