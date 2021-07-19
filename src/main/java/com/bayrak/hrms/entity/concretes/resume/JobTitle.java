package com.bayrak.hrms.entity.concretes.resume;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@Table(name="job_titles")
@NoArgsConstructor
@AllArgsConstructor
public class JobTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank(message="Job title cannot be blank")
    @Column(name="title")
    private String title;

    public JobTitle(String title) {
        this.title = title;
    }
}
