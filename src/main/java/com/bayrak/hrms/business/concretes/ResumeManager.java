package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.ResumeService;
import com.bayrak.hrms.core.utilities.results.*;
import com.bayrak.hrms.dataAccess.abstracts.*;
import com.bayrak.hrms.entity.concretes.Candidate;
import com.bayrak.hrms.entity.concretes.enums.SocialLink;
import com.bayrak.hrms.entity.concretes.resume.*;
import com.bayrak.hrms.entity.dto.resume.ResumeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ResumeManager implements ResumeService {

    private final ResumeDao resumeDao;
    private final CandidateDao candidateDao;
    private final ProgrammingLanguageDao programmingLanguageDao;
    private final SchoolDao schoolDao;
    private final ResumeLanguagLevelDao resumeLanguagLevelDao;
    private final LanguageDao languageDao;
    private final JobTitleDao jobTitleDao;



    @Override
    public DataResult<Resume> getById(int id) {
        Optional<Resume> result = resumeDao.findById(id);
        if(result.isEmpty()){
            return new ErrorDataResult<>("Resume not found");
        }

        return new SuccessDataResult<>(result.get());
    }

    @Override
    public DataResult<List<Resume>> getAll() {
        List<Resume> all = resumeDao.findAll();
        if (all.size()>0) {
            return new SuccessDataResult<>(all);
        }
        return new ErrorDataResult<>("Resume list is empty");
    }

    @Override
    @Transactional
    public Result save(ResumeRequestDto resumeRequestDto) {

        Resume resume = new Resume();

        Optional<Candidate> candidateOptional =
                candidateDao.findById(resumeRequestDto.getCandidateId());

        if (candidateOptional.isEmpty()) {
            return new ErrorResult("Candidate not found");
        }

        resume.setCandidate(candidateOptional.get());

        resumeRequestDto.getResumeLanguageLevels().stream().forEach(
                i ->{
                    if (languageDao.existsByName(i.getLanguage())) {
                        Language language = languageDao.findByName(i.getLanguage());
                        resume.getResumeLanguageLevels().add(ResumeLanguageLevel.builder()
                                .language(language)
                                .resume(resume)
                                .languageLevel(i.getLanguageLevel()).build());
                    }else{
                        resume.getResumeLanguageLevels().add(ResumeLanguageLevel.builder()
                                .resume(resume)
                                .language(Language.builder()
                                        .name(i.getLanguage())
                                        .build())
                                .languageLevel(i.getLanguageLevel()
                                ).build());
                    }
                }
        );



        resumeRequestDto.getSchoolList().forEach(
                i -> {
                    School school = new School(i.getName(),resume);
                    if (i.isGraduated() && i.getGraduateDate()!= null) {
                        school.setGraduated(true);
                        school.setGraduateDate(i.getGraduateDate());
                    }
                    resume.getSchoolList().add(school);
                }
        );


        resumeRequestDto.getProgrammingLanguages().stream().forEach(
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

        resumeRequestDto.getJobExperienceList().stream().forEach(
                i-> {
                    i.setResume(resume);
                    if (jobTitleDao.existsByTitle(i.getJobTitle().getTitle())) {
                        i.setJobTitle(jobTitleDao.findByTitle(i.getJobTitle().getTitle()));
                    }else{
                        i.setJobTitle(new JobTitle(i.getJobTitle().getTitle()));
                    }
                    resume.getJobExperience().add(i);
                }
        );


        if(resumeRequestDto.getPhotoUrl()!= null){
            resume.setProfile_picture(resumeRequestDto.getPhotoUrl());
        }
        if(resumeRequestDto.getJobExperienceList()!=null){
            resume.setJobExperience(resumeRequestDto.getJobExperienceList());
        }

        if(resumeRequestDto.getGithubLink()!= null){
            resume.getSocialLinks().put(SocialLink.GITHUB,
                    resumeRequestDto.getGithubLink());
        }
        if(resumeRequestDto.getLinkedinLink()!= null){
            resume.getSocialLinks().put(SocialLink.GITHUB,
                    resumeRequestDto.getLinkedinLink());
        }
        if(resumeRequestDto.getCoverLetter()!= null){
            resume.setCoverLetter(resumeRequestDto.getCoverLetter());
        }

        resumeDao.save(resume);
        return new SuccessResult();
    }

}
