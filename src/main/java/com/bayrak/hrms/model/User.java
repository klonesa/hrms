package com.bayrak.hrms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(of = {"id","email"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="users")
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="email",unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message="password cannot be blank")
    @Column(name="password")
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
