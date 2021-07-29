package com.bayrak.hrms.model.resume;


import com.bayrak.hrms.model.Candidate;
import com.bayrak.hrms.model.SocialLink;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JsonIgnore
    private Candidate candidate;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<School> schoolList = new HashSet<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private Set<JobExperience> jobExperience = new HashSet<>();

    @OneToMany(mappedBy = "resume",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private Set<ResumeLanguageLevel> resumeLanguageLevels = new HashSet<>();

    @ElementCollection
    @MapKeyColumn(name = "platform")
    @CollectionTable(name = "resume_social_links",joinColumns = @JoinColumn(name = "resume_id"))
    private Map<SocialLink, String> socialLinks = new HashMap<>();

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<ProgrammingLanguage> programmingLanguages = new HashSet<>();

    @Column(name = "cover_letter",length = 2000)
    private String coverLetter;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ResumePhoto photo;

    public Resume(Candidate candidate, String coverLetter) {
        this.candidate = candidate;
        this.coverLetter = coverLetter;
    }
}
