package com.bayrak.hrms.service;

import com.bayrak.hrms.exception.UploadImageErrorException;
import com.bayrak.hrms.model.resume.Resume;
import com.bayrak.hrms.model.resume.ResumePhoto;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadImageService {

    private final Cloudinary cloudinary;

    public  Map uploadImage(String imageUri) {
        try {
            return cloudinary.uploader().upload(imageUri, ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
            throw new UploadImageErrorException(e.getLocalizedMessage());
        }
    }

    public Resume uploadImageToResume(String photoUri, Resume resume){
        Map upload = uploadImage(photoUri);
        resume.setPhoto(ResumePhoto.builder()
                .photoUrl((String) upload.get("secure_url"))
                .createdAt((String) upload.get("created_at"))
                .bytes((Integer) upload.get("bytes"))
                .publicId((String) upload.get("public_id"))
                .resourceType((String) upload.get("resource_type"))
                .build());
        return resume;
    }



}
