package com.bayrak.hrms.entity.concretes.resume;


import com.bayrak.hrms.entity.concretes.Candidate;
import com.bayrak.hrms.entity.concretes.ResumePhoto;
import com.bayrak.hrms.entity.concretes.enums.SocialLink;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resumes")
@Builder
@EqualsAndHashCode(of = {"id","candidate"})
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_seq_generator")
    @SequenceGenerator(name = "resume_seq_generator", sequenceName = "resume_id_seq", allocationSize = 1)
    @Column(name = "id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    @JsonIgnore/*Properties({"hibernateLazyInitializer", "handler"})*/
    private Candidate candidate;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<School> schoolList = new HashSet<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<JobExperience> jobExperience = new HashSet<>();

    @OneToMany(mappedBy = "resume",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<ResumeLanguageLevel> resumeLanguageLevels = new HashSet<>();

    @ElementCollection
    @MapKeyColumn(name = "platform")
    @CollectionTable(name = "resume_social_links",joinColumns = @JoinColumn(name = "resume_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Map<SocialLink, String> socialLinks = new HashMap<>();

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<ProgrammingLanguage> programmingLanguages = new HashSet<>();


    @Lob
    @Column(name = "cover_letter")
    private String coverLetter;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private ResumePhoto photo;

    public Resume(Candidate candidate, String coverLetter) {
        this.candidate = candidate;
        this.coverLetter = coverLetter;
    }
}
