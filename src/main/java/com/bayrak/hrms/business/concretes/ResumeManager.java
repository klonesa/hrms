package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.ResumeService;
import com.bayrak.hrms.core.utilities.results.*;
import com.bayrak.hrms.dataAccess.abstracts.*;
import com.bayrak.hrms.entity.concretes.Candidate;
import com.bayrak.hrms.entity.concretes.enums.converters.LanguageLevelConverter;
import com.bayrak.hrms.entity.concretes.resume.Language;
import com.bayrak.hrms.entity.concretes.resume.ProgrammingLanguage;
import com.bayrak.hrms.entity.concretes.resume.Resume;
import com.bayrak.hrms.entity.concretes.resume.ResumeLanguageLevel;
import com.bayrak.hrms.entity.convertors.convertorImpl.ConvertResume;
import com.bayrak.hrms.entity.dto.resume.ResumeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeManager implements ResumeService {

    private final ResumeDao resumeDao;
    private final CandidateDao candidateDao;
    private final ConvertResume convertResume;
    private final LanguageDao languageDao;
    private final LanguageLevelConverter languageLevelConverter;
    private final ProgrammingLanguageDao programmingLanguageDao;
    private final JobTitleDao jobTitleDao;

    @Override
    public DataResult<ResumeDto> getById(int id) {

        if(resumeDao.findById(id).isEmpty()){
            return new ErrorDataResult<>("Resume not found");
        }

        return new SuccessDataResult<>(convertResume.convertEntityToDto(resumeDao.findById(id).get()));
    }

    @Override
    public DataResult<List<ResumeDto>> getAll() {
        List<Resume> all = resumeDao.findAll();
        List<ResumeDto> result = new ArrayList<>();

        if (all.size() <= 0) {
            return new ErrorDataResult<>("Resume list is empty");
        }

        all.forEach(i-> result.add(convertResume.convertEntityToDto(i)));

        return new SuccessDataResult<>(result);
    }

    @Override
    @Transactional
    public Result save(ResumeDto resumeDto) {

        Optional<Candidate> candidateOptional =
                candidateDao.findById(resumeDto.getCandidateId());

        if (candidateOptional.isEmpty()) {
            return new ErrorResult("Candidate not found");
        }

        Resume resume = convertResume.convertDtoToEntity(resumeDto);

        resume.setCandidate(candidateOptional.get());


        resume.setResumeLanguageLevels(
            resume.getResumeLanguageLevels().stream().map(
                    i-> {
                        if (languageDao.existsByName(i.getLanguage().getName())) {
                            Language language = languageDao.findByName(i.getLanguage().getName());
                            return ResumeLanguageLevel.builder()
                                    .language(language)
                                    .resume(resume)
                                    .languageLevel(languageLevelConverter.convertToEntityAttribute(
                                            String.valueOf(i.getLanguageLevel()
                                                    .getValue())))
                                    .build();
                        }
                        return i;
                    }
            ).collect(Collectors.toSet())
        );

        resume.setProgrammingLanguages(
            resume.getProgrammingLanguages().stream().map(
                    i -> {
                        if(programmingLanguageDao.existsByName(i.getName())){
                            return programmingLanguageDao.findByName(i.getName());
                        }
                       return i;
                    }
            ).collect(Collectors.toSet())
        );

        resumeDao.save(resume);
        return new SuccessResult();
    }

}
