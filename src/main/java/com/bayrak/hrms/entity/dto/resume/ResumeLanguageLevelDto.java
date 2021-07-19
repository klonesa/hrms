package com.bayrak.hrms.entity.dto.resume;

import com.bayrak.hrms.entity.concretes.enums.LanguageLevel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeLanguageLevelDto {
    private String language;
    private LanguageLevel languageLevel;
}
