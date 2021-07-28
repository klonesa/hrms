package com.bayrak.hrms.dto.resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumePhotoDto {
    private int resumeId;
    private String photoUri;
}
