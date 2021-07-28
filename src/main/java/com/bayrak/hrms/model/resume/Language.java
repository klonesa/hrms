package com.bayrak.hrms.model.resume;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "languages")
@Builder
@EqualsAndHashCode(of = {"id","name"})
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "languages_seq_generator")
    @SequenceGenerator(name="languages_seq_generator",sequenceName = "languages_id_seq",allocationSize = 1)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @Column(name = "name",length = 50,nullable = false,unique = true,updatable = false)
    private String name;

}
