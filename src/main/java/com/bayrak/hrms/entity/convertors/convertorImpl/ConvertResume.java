package com.bayrak.hrms.entity.convertors.convertorImpl;

import com.bayrak.hrms.dataAccess.abstracts.JobTitleDao;
import com.bayrak.hrms.dataAccess.abstracts.LanguageDao;
import com.bayrak.hrms.dataAccess.abstracts.ProgrammingLanguageDao;
import com.bayrak.hrms.entity.concretes.enums.converters.LanguageLevelConverter;
import com.bayrak.hrms.entity.concretes.resume.*;
import com.bayrak.hrms.entity.convertors.Convertor;
import com.bayrak.hrms.entity.dto.resume.JobExperienceDto;
import com.bayrak.hrms.entity.dto.resume.ResumeDto;
import com.bayrak.hrms.entity.dto.resume.ResumeLanguageDto;
import com.bayrak.hrms.entity.dto.resume.SchoolDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.bayrak.hrms.entity.dto.resume.ResumeDto.builder;

@Component
@RequiredArgsConstructor
public class ConvertResume implements Convertor<Resume, ResumeDto> {

    private final LanguageLevelConverter languageLevelConverter;
    private final LanguageDao languageDao;
    private final ProgrammingLanguageDao programmingLanguageDao;
    private final JobTitleDao jobTitleDao;


    @Override
    public ResumeDto convertEntityToDto(Resume resume) {

        ResumeDto result = builder()
                .resumeId(resume.getId())
                .coverLetter(resume.getCoverLetter())
                .photoUrl(resume.getProfile_picture())
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
        return result;
    }

    @Override
    public Resume convertDtoToEntity(ResumeDto resumeDto) {

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

                    je.setJobTitle(new JobTitle(i.getJobTitle()));
                    resume.getJobExperience().add(je);
                }
        );

        resumeDto.getLinks().forEach(
                (k, v) -> resume.getSocialLinks().put(k, v)
        );

        if(resumeDto.getPhotoUrl()!= null){
            resume.setProfile_picture(resumeDto.getPhotoUrl());
        }
        if(resumeDto.getCoverLetter()!= null){
            resume.setCoverLetter(resumeDto.getCoverLetter());
        }

        return resume;
    }

/*    @Override
    public Resume convertDtoToEntity(ResumeDto resumeDto) {

        Resume resume = new Resume();

        resumeDto.getLanguages().forEach(
                i ->{
                    if (languageDao.existsByName(i.getLanguage())) {
                        Language language = languageDao.findByName(i.getLanguage());
                        resume.getResumeLanguageLevels().add(ResumeLanguageLevel.builder()
                                .language(language)
                                .resume(resume)
                                .languageLevel(languageLevelConverter
                                        .convertToEntityAttribute(i.getLevel()))
                                .build());
                    }else{
                        resume.getResumeLanguageLevels().add(ResumeLanguageLevel.builder()
                                .resume(resume)
                                .language(Language.builder()
                                        .name(i.getLanguage())
                                        .build())
                                .languageLevel(languageLevelConverter
                                        .convertToEntityAttribute(i.getLevel()))
                                .build());
                    }
                }
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
                    if(programmingLanguageDao.existsByName(i)){
                        resume.getProgrammingLanguages().add(programmingLanguageDao.findByName(i));
                    }else{
                        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
                        programmingLanguage.setName(i);
                        resume.getProgrammingLanguages().add(programmingLanguage);
                    }
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
                    if (jobTitleDao.existsByTitle(i.getJobTitle())) {
                        je.setJobTitle(jobTitleDao.findByTitle(i.getJobTitle()));
                    }else{
                        je.setJobTitle(new JobTitle(i.getJobTitle()));
                    }
                    resume.getJobExperience().add(je);
                }
        );

        resumeDto.getLinks().forEach(
                (k, v) -> resume.getSocialLinks().put(k, v)
        );

        if(resumeDto.getPhotoUrl()!= null){
            resume.setProfile_picture(resumeDto.getPhotoUrl());
        }
        if(resumeDto.getCoverLetter()!= null){
            resume.setCoverLetter(resumeDto.getCoverLetter());
        }

        return resume;
    }*/
}
