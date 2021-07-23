package com.bayrak.hrms.entity.concretes.resume;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(of = {"id","name"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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
