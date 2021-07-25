package com.bayrak.hrms.entity.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="resume_photos")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
public class ResumePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "resume_photos_seq_generator")
    @SequenceGenerator(name="resume_photos_seq_generator",sequenceName = "resume_photos_id_seq",allocationSize = 1)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @Column(name = "photo_url",nullable = false)
    @NotBlank
    @NotNull
    private String photoUrl;



}
