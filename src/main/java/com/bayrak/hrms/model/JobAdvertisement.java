package com.bayrak.hrms.model;

import com.bayrak.hrms.model.resume.JobTitle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@Table(name="job_advertisement")
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_title_id")
    private JobTitle JobTitle;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="description")
    private String description;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = City.class ,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", referencedColumnName =  "id" ,nullable = false)
    private City city;

    @Column(name="min_salary")
    private int minSalary;

    @Column(name="max_salary")
    private int maxSalary;

    @Column(name="open_position_number")
    private int openPositionNumber;

    @Temporal(TemporalType.DATE)
    @Column(name="created_date")
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name="close_date")
    private Date closeDate;

    @Column(name="is_active")
    private boolean isActive=true;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employer_id",nullable = false)
    private Employer employer;

    public JobAdvertisement(JobTitle jobTitle,
                            String description, City city,
                            int minSalary, int maxSalary,
                            int openPositionNumber, Date closeDate,
                            Employer employer) {}
}