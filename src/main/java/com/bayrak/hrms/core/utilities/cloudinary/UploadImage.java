package com.bayrak.hrms.core.utilities.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadImage {

    private final Cloudinary cloudinary;

    public  Map upload(String imageUri) throws IOException {
        return cloudinary.uploader().upload(imageUri, ObjectUtils.emptyMap());
    }
}
