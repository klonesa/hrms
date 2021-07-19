package com.bayrak.hrms.entity.concretes.resume;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_experiences")
@Builder
@EqualsAndHashCode
public class JobExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "job_experience_seq_generator")
    @SequenceGenerator(name="job_experience_seq_generator",sequenceName = "job_experience_id_seq",allocationSize = 1)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id",nullable = false)
    @JsonIgnore
    private Resume resume;

    @Column(name = "company_name",nullable = false,length = 100)
    private String companyName;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "job_title_id")
    private JobTitle jobTitle;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date",nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @Override
    public String toString() {
        return "JobExperience{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", jobTitle=" + jobTitle.getTitle() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

