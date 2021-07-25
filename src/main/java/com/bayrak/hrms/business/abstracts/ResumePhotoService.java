package com.bayrak.hrms.business.abstracts;


import com.bayrak.hrms.core.utilities.results.DataResult;

import java.io.IOException;

public interface ResumePhotoService {

    DataResult addPhoto(int resumeId, String photoUri) throws IOException;

    DataResult updatePhoto(int resumeId, String photoUri) throws IOException;
}
