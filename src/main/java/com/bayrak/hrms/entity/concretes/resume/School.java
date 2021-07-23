package com.bayrak.hrms.entity.concretes.resume;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schools")
@Builder
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "schools_seq_generator")
    @SequenceGenerator(name="schools_seq_generator",sequenceName = "schools_id_seq",allocationSize = 1)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Resume resume;

    @Temporal(TemporalType.DATE)
    @Column(name = "graduate_date",nullable = true)
    private Date graduateDate;

    @Column(name = "is_graduated")
    private boolean isGraduated=false;

    public School(String name, Resume resume) {
        this.name = name;
        this.resume = resume;
    }

    public School(String name, Resume resume, Date graduateDate) {
        this.name = name;
        this.resume = resume;
        this.graduateDate = graduateDate;
        this.isGraduated = true;
    }

}
