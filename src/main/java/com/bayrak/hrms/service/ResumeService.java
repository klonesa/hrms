package com.bayrak.hrms.service;

import com.bayrak.hrms.dto.resume.ResumeDto;
import com.bayrak.hrms.exception.ResumeNotFoundException;
import com.bayrak.hrms.model.Candidate;
import com.bayrak.hrms.model.resume.Language;
import com.bayrak.hrms.model.resume.Resume;
import com.bayrak.hrms.model.resume.ResumeLanguageLevel;
import com.bayrak.hrms.model.resume.ResumePhoto;
import com.bayrak.hrms.repository.LanguageDao;
import com.bayrak.hrms.repository.ProgrammingLanguageDao;
import com.bayrak.hrms.repository.ResumeDao;
import com.bayrak.hrms.dto.convertor.LanguageLevelConverter;
import com.bayrak.hrms.dto.convertor.ConvertResume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeDao resumeDao;
    private final CandidateService candidateService;
    private final LanguageDao languageDao;
    private final ConvertResume convertResume;
    private final LanguageLevelConverter languageLevelConverter;
    private final ProgrammingLanguageDao programmingLanguageDao;
    private final UploadImageService uploadImageService;

    public ResumeDto getById(int id) {

        return convertResume.EntityToDto(resumeDao.findById(id).orElseThrow(
                () -> { throw new ResumeNotFoundException(id); }
        ));
    }

    public List<ResumeDto> getAll() {
        return resumeDao.findAll().stream()
                .map(convertResume::EntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ResumeDto save(ResumeDto resumeDto) {

        Candidate candidate = candidateService.findById(resumeDto.getCandidateId());

        Resume resume = convertResume.DtoToEntity(resumeDto);

        resume.setCandidate(candidate);

        resume.setResumeLanguageLevels(
            resume.getResumeLanguageLevels().stream().map(
                    i-> {
                        if (languageDao.existsByNameIgnoreCase(i.getLanguage().getName())) {
                            Language language = languageDao.findByNameIgnoreCase(i.getLanguage().getName());
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
                        if(programmingLanguageDao.existsByNameIgnoreCase(i.getName())){
                            return programmingLanguageDao.findByNameIgnoreCase(i.getName());
                        }
                       return i;
                    }
            ).collect(Collectors.toSet())
        );

        if (resumeDto.getPhotoUri() != null) {
            final Map upload = uploadImageService.uploadImage(resumeDto.getPhotoUri());
            resume.setPhoto(ResumePhoto.builder()
                    .photoUrl((String) upload.get("secure_url"))
                    .createdAt((String) upload.get("created_at"))
                    .bytes((Integer) upload.get("bytes"))
                    .publicId((String) upload.get("public_id"))
                    .resourceType((String) upload.get("resource_type"))
                    .build());
        }
        resumeDao.save(resume);
        return convertResume.EntityToDto(resume);
    }

}
