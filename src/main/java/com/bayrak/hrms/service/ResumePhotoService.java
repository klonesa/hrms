package com.bayrak.hrms.service;

import com.bayrak.hrms.exception.ResumeAlreadyHaveImageException;
import com.bayrak.hrms.exception.ResumeDoesNotHaveImageException;
import com.bayrak.hrms.exception.ResumeNotFoundException;
import com.bayrak.hrms.model.resume.Resume;
import com.bayrak.hrms.model.resume.ResumePhoto;
import com.bayrak.hrms.repository.ResumeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumePhotoService {

    private final ResumeDao resumeDao;
    private final UploadImageService uploadImageService;

    public ResumePhoto addPhoto(int resumeId, String photoUri){

        Resume resume = resumeDao.findById(resumeId).map(
                i -> {
                    if (i.getPhoto() != null) {
                        throw new ResumeAlreadyHaveImageException(resumeId);
                    }
                    resumeDao.save(uploadImageService
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
                    resumeDao.save(uploadImageService
                            .uploadImageToResume(photoUri,i));
                    return i;
                }
        ).orElseThrow(
                () -> {throw new ResumeNotFoundException(resumeId);}
        );
        return resume.getPhoto();

    }
}
