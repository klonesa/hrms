package com.bayrak.hrms.dto.convertor;

import com.bayrak.hrms.dto.convertor.LanguageLevelConverter;
import com.bayrak.hrms.model.resume.*;
import com.bayrak.hrms.dto.convertor.Convertor;
import com.bayrak.hrms.dto.resume.JobExperienceDto;
import com.bayrak.hrms.dto.resume.ResumeDto;
import com.bayrak.hrms.dto.resume.ResumeLanguageDto;
import com.bayrak.hrms.dto.resume.SchoolDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ConvertResume implements Convertor<Resume, ResumeDto> {

    private final LanguageLevelConverter languageLevelConverter;

    @Override
    public ResumeDto EntityToDto(Resume resume) {

        ResumeDto result = ResumeDto.builder()
                .resumeId(resume.getId())
                .coverLetter(resume.getCoverLetter())
                .build();

        result.setCandidateId(resume.getCandidate().getId());

        resume.getJobExperience().forEach(
                i-> result.getJobExperiences().add(JobExperienceDto.builder()
                        .companyName(i.getCompanyName())
                        .id(i.getId())
                        .jobTitle(i.getJobTitle().getTitle())
                        .startDate(i.getStartDate())
                        .endDate(i.getEndDate())
                        .build())
        );

        resume.getSchoolList().forEach(
                i -> result.getSchools().add(SchoolDto.builder()
                        .name(i.getName())
                        .isGraduated(i.isGraduated())
                        .graduateDate(i.getGraduateDate())
                        .build()
                )
        );

        result.setLinks(resume.getSocialLinks());

        resume.getProgrammingLanguages().forEach(
                i-> result.getProgammingLanguages().add(
                        i.getName()
                )
        );

        resume.getResumeLanguageLevels().forEach(
                i-> result.getLanguages().add(ResumeLanguageDto.builder()
                        .language(i.getLanguage().getName())
                        .level(languageLevelConverter.convertToDatabaseColumn(i.getLanguageLevel()))
                        .build())
        );

        if(resume.getPhoto()!=null && resume.getPhoto().getPhotoUrl()!=null){
            result.setPhotoUrl(resume.getPhoto().getPhotoUrl());
        }

        return result;
    }

    @Override
    public Resume DtoToEntity(ResumeDto resumeDto) {

        Resume resume = new Resume();

        resumeDto.getLanguages().forEach(
                i -> resume.getResumeLanguageLevels().add(ResumeLanguageLevel.builder()
                        .resume(resume)
                        .language(Language.builder()
                                .name(i.getLanguage())
                                .build())
                        .languageLevel(languageLevelConverter
                                .convertToEntityAttribute(i.getLevel()))
                        .build())
        );

        resumeDto.getSchools().forEach(
                i -> {
                    School school = new School(i.getName(),resume);
                    if (i.isGraduated() && i.getGraduateDate()!= null) {
                        school.setGraduated(true);
                        school.setGraduateDate(i.getGraduateDate());
                    }
                    resume.getSchoolList().add(school);
                }
        );

        resumeDto.getProgammingLanguages().forEach(
                i-> {
                    ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
                    programmingLanguage.setName(i);
                    resume.getProgrammingLanguages().add(programmingLanguage);
                }
        );

        resumeDto.getJobExperiences().forEach(
                i-> {
                    JobExperience je =  JobExperience.builder()
                            .companyName(i.getCompanyName())
                            .startDate(i.getStartDate())
                            .endDate(i.getEndDate())
                            .resume(resume)
                            .build();

                    je.setJobTitle(new JobTitle(0,i.getJobTitle()));
                    resume.getJobExperience().add(je);
                }
        );

        resumeDto.getLinks().forEach(
                (k, v) -> resume.getSocialLinks().put(k, v)
        );

        if(resumeDto.getCoverLetter()!= null){
            resume.setCoverLetter(resumeDto.getCoverLetter());
        }

        return resume;
    }

}
