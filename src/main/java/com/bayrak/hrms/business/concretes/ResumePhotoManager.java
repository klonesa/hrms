package com.bayrak.hrms.business.concretes;

import com.bayrak.hrms.business.abstracts.ResumePhotoService;
import com.bayrak.hrms.core.utilities.cloudinary.UploadImage;
import com.bayrak.hrms.core.utilities.results.DataResult;
import com.bayrak.hrms.core.utilities.results.ErrorDataResult;
import com.bayrak.hrms.core.utilities.results.SuccessDataResult;
import com.bayrak.hrms.dataAccess.abstracts.ResumeDao;
import com.bayrak.hrms.dataAccess.abstracts.ResumePhotoDao;
import com.bayrak.hrms.entity.concretes.ResumePhoto;
import com.bayrak.hrms.entity.concretes.resume.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ResumePhotoManager implements ResumePhotoService {

    private final ResumeDao resumeDao;
    private final UploadImage uploadImage;
    private final ResumePhotoDao resumePhotoDao;


    @Override
    public DataResult addPhoto(int resumeId, String photoUri) throws IOException {

        if (resumeDao.findById(resumeId).isEmpty()) {
            return new ErrorDataResult("Resume not found");
        }

        Resume resume = resumeDao.findById(resumeId).get();
        if (resume.getPhoto() != null) {
            return new ErrorDataResult("This resume already have a photo");
        }

        resume.setPhoto(ResumePhoto.builder()
                .photoUrl((String)uploadImage.upload(photoUri).get("secure_url"))
                .build());
        resumeDao.save(resume);
        return new SuccessDataResult(resume.getPhoto().getPhotoUrl());
    }

    @Override
    public DataResult updatePhoto(int resumeId, String photoUri) throws IOException {

        if (!resumeDao.existsById(resumeId)) {
            return new ErrorDataResult("Resume not found");
        }

        Resume resume = resumeDao.findById(resumeId).get();
        if (resume.getPhoto() == null) {
            return new ErrorDataResult("This resume have no photo");
        }

        resume.setPhoto(ResumePhoto.builder()
                .photoUrl((String)uploadImage.upload(photoUri).get("secure_url"))
                .build());
        resumeDao.save(resume);
        return new SuccessDataResult(resume.getPhoto().getPhotoUrl());

    }
}
