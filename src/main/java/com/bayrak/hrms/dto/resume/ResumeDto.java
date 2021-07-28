package com.bayrak.hrms.dto.resume;

import com.bayrak.hrms.model.SocialLink;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int resumeId;

    private int candidateId;

    @Builder.Default
    private Set<JobExperienceDto> jobExperiences = new HashSet<>();

    @Builder.Default
    private Set<ResumeLanguageDto> languages = new HashSet<>();

    @Builder.Default
    private Set<SchoolDto> schools = new HashSet<>();

    @Builder.Default
    private Map<SocialLink, String> links = new HashMap<>();

    @Builder.Default
    private Set<String> progammingLanguages = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String photoUri;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String photoUrl;

    private String coverLetter;
}
