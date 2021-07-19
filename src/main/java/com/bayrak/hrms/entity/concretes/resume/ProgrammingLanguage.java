package com.bayrak.hrms.entity.concretes.resume;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "programming_languages")
public class ProgrammingLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "programming_languages_seq_generator")
    @SequenceGenerator(name="programming_languages_seq_generator",sequenceName = "programming_languages_id_seq",allocationSize = 1)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @Column(name = "name",unique = true,length = 100,nullable = false)
    private String name;

}
