package com.bayrak.hrms.tests;

import com.bayrak.hrms.dataAccess.abstracts.CandidateDao;
import com.bayrak.hrms.dataAccess.abstracts.JobTitleDao;
import com.bayrak.hrms.dataAccess.abstracts.ResumeDao;
import com.bayrak.hrms.entity.concretes.Candidate;
import com.bayrak.hrms.entity.concretes.enums.LanguageLevel;
import com.bayrak.hrms.entity.concretes.enums.SocialLink;
import com.bayrak.hrms.entity.concretes.resume.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class Test {

    @Bean
    CommandLineRunner commandLineRunner(ResumeDao resumeDao, CandidateDao candidateDao, JobTitleDao jobTitleDao){
        return args -> {





/*            Enum.valueOf(LanguageLevel ll, "asd");

            Resume resumerslt = resumeDao.findById(14).get();

            System.out.println();
            resumerslt.getJobExperience().stream().forEach(
                    i -> System.out.println(i)
            );

            System.out.println(resumerslt.getCandidate());*/
/*
            Candidate cd = candidateDao.findById(1).get();

            System.out.println(cd.getFirstName());
            Resume resume = new Resume(cd,"cover letter");




            JobExperience je = JobExperience.builder().
                    resume(resume).
                    companyName("Company")
                    .startDate(new Date()).endDate(new Date())
            .jobTitle(jobTitleDao.findById(1).get()).build();



            Language lg = new Language();
            lg.setName("English");



            ResumeLanguageLevel rll = new ResumeLanguageLevel();
            rll.setLanguage(lg);
            rll.setLanguageLevel(LanguageLevel.FOUR);

            ProgrammingLanguage pl = new ProgrammingLanguage();
            pl.setName("JAVA");


            School school = School.builder().name("ŞÖKAÖL")
                    .graduateDate(new Date())
                    .isGraduated(true)
                    .build();

            rll.setLanguage(lg);
             resume.getJobExperience().add(je);
            resume.getResumeLanguageLevels().add(rll);
            resume.getProgrammingLanguages().add(pl);
            resume.getSocialLinks().put(SocialLink.GITHUB, "bedirhannbayrak.github.com");
            resume.getSchoolList().add(school);
            resume.setProfile_picture("https://profilepic.com/465546");

            resumeDao.save(resume);*/

        };
    }

}
