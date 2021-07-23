package com.bayrak.hrms.entity.dto.resume;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class ResumeLanguageDto {
    private String language;
    private int level;
}
