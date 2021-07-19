package com.bayrak.hrms.entity.concretes.resume;

import com.bayrak.hrms.entity.concretes.enums.LanguageLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="resume_language_levels",uniqueConstraints =@UniqueConstraint(columnNames = {"resume_id","language_id"}) )
@Builder
public class ResumeLanguageLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "resume_language_level_seq_generator")
    @SequenceGenerator(name="resume_language_level_seq_generator",sequenceName = "resume_language_level_id_seq",allocationSize = 1)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    private Resume resume;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "language_id")
    private  Language language;

    @Enumerated(EnumType.STRING)
    private LanguageLevel languageLevel;


}
