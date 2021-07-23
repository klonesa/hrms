package com.bayrak.hrms.entity.dto.resume;


import com.bayrak.hrms.entity.concretes.resume.JobExperience;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeRequestDto {

    private int candidateId; //
    Set<JobExperience> jobExperienceList; //
    private String photoUrl; //
    Set<String> programmingLanguages;//
    Set<ResumeLanguageLevelDto> resumeLanguageLevels;//
    Set<SchoolDto> schoolList;//
    private String githubLink;//
    private String linkedinLink;//
    private String coverLetter;//
}
