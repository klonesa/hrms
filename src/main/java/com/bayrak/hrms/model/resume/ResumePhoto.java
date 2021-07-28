package com.bayrak.hrms.model.resume;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
@ToString
public class ResumePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "resume_photos_seq_generator")
    @SequenceGenerator(name="resume_photos_seq_generator",sequenceName = "resume_photos_id_seq",allocationSize = 1)
    @Column(name="id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @Column(name = "url",nullable = false)
    @NotBlank
    @NotNull
    private String photoUrl;

    @Column(name = "resurce_type")
    private String resourceType;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "public_id")
    private String publicId;
    private Integer bytes;

}
