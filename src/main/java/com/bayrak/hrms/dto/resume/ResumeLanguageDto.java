package com.bayrak.hrms.dto.resume;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class ResumeLanguageDto {
    private String language;
    private String level;
}
