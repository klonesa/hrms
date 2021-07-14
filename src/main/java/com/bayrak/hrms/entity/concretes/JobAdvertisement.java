package com.bayrak.hrms.entity.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name="job_advertisement")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = JobTitle.class ,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_title_id", referencedColumnName =  "id" ,nullable = false)
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
    @ManyToOne(targetEntity = Employer.class ,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employer_id", referencedColumnName =  "id" ,nullable = false)
    private Employer employer;


    public JobAdvertisement(com.bayrak.hrms.entity.concretes.JobTitle jobTitle, String description, City city, int minSalary, int maxSalary, int openPositionNumber, Date closeDate, Employer employer) {
        JobTitle = jobTitle;
        this.description = description;
        this.city = city;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.openPositionNumber = openPositionNumber;
        this.createdDate = new Date();
        this.closeDate = closeDate;
        this.employer = employer;
        this.isActive=true;
    }
}
