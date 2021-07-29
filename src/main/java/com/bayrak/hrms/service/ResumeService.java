package com.bayrak.hrms.service;

import com.bayrak.hrms.dto.resume.ResumeDto;
import com.bayrak.hrms.exception.ResumeAlreadyHaveImageException;
import com.bayrak.hrms.exception.ResumeDoesNotHaveImageException;
import com.bayrak.hrms.exception.ResumeNotFoundException;
import com.bayrak.hrms.model.Candidate;
import com.bayrak.hrms.model.resume.Language;
import com.bayrak.hrms.model.resume.Resume;
import com.bayrak.hrms.model.resume.ResumeLanguageLevel;
import com.bayrak.hrms.model.resume.ResumePhoto;
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
    private final LanguageService languageService;
    private final ProgrammingLanguageService programmingLanguageService;
    private final ImageService imageService;
    private final LanguageLevelConverter languageLevelConverter;
    private final ConvertResume convertResume;

    public ResumeDto findByIdConverDto(int id) {
        return convertResume.EntityToDto(resumeDao.findById(id).orElseThrow(
                () -> { throw new ResumeNotFoundException(id); }
        ));
    }

    protected Resume findById(int id) {
        return resumeDao.findById(id).orElseThrow(
                () -> { throw new ResumeNotFoundException(id); }
        );
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
                        if (languageService.existsByNameIgnoreCase(i.getLanguage().getName())) {
                            Language language = languageService.findByNameIgnoreCase(i.getLanguage().getName());
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
                        if(programmingLanguageService.existsByNameIgnoreCase(i.getName())){
                            return programmingLanguageService.findByNameIgnoreCase(i.getName());
                        }
                       return i;
                    }
            ).collect(Collectors.toSet())
        );

        if (resumeDto.getPhotoUri() != null) {
            final Map upload = imageService.uploadImage(resumeDto.getPhotoUri());
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

    public ResumePhoto addPhoto(int resumeId, String photoUri){

        Resume resume = resumeDao.findById(resumeId).map(
                i -> {
                    if (i.getPhoto() != null) {
                        throw new ResumeAlreadyHaveImageException(resumeId);
                    }
                    resumeDao.save(imageService
                            .uploadImageToResume(photoUri,i));
                    return i;
                }
        ).orElseThrow(
                () -> {throw new ResumeNotFoundException(resumeId);}
        );
        return resume.getPhoto();
    }

    public ResumePhoto updatePhoto(int resumeId, String photoUri) {
        Resume resume = resumeDao.findById(resumeId).map(
                i -> {
                    if (i.getPhoto() == null) {
                        throw new ResumeDoesNotHaveImageException(resumeId);
                    }
                    resumeDao.save(imageService
                            .uploadImageToResume(photoUri,i));
                    return i;
                }
        ).orElseThrow(
                () -> {throw new ResumeNotFoundException(resumeId);}
        );
        return resume.getPhoto();
    }

    protected void save(Resume resume) {
        resumeDao.save(resume);
    }
}
