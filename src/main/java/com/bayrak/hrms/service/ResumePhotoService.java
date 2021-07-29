package com.bayrak.hrms.service;

import com.bayrak.hrms.model.resume.Resume;
import com.bayrak.hrms.model.resume.ResumePhoto;
import com.bayrak.hrms.repository.ResumePhotoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumePhotoService {


    private final ResumePhotoDao resumePhotoDao;
    private final ResumeService resumeService;
    private final ImageService imageService;

    public ResumePhoto addPhoto(int resumeId, String photoUri){
        return resumeService.addPhoto(resumeId,photoUri);
    }

    public ResumePhoto updatePhoto(int resumeId, String photoUri) {
        return resumeService.updatePhoto(resumeId,photoUri);
    }

    @Transactional
    public void deletePhoto(int resumeId) {
        Resume resume = resumeService.findById(resumeId);
        final ResumePhoto photo = resume.getPhoto();
        imageService.deleteImage(
                photo);
        resume.setPhoto(null);
        resumeService.save(resume);
        resumePhotoDao.deleteById(photo.getId());
    }
}
