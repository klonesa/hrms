package com.bayrak.hrms.entity.concretes.enums.converters;

import com.bayrak.hrms.entity.concretes.enums.LanguageLevel;
import org.springframework.stereotype.Service;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Service
@Converter(autoApply = true)
public class LanguageLevelConverter implements AttributeConverter<LanguageLevel, String> {

    @Override
    public String convertToDatabaseColumn(LanguageLevel languageLevel) {
        if (languageLevel == null) {
            return null;
        }
        return String.valueOf(languageLevel.getValue());
    }

    @Override
    public LanguageLevel convertToEntityAttribute(String level) {
        if (level == null) {
            return null;
        }
        return Stream.of(LanguageLevel.values())
                .filter(c -> c.getValue()==Integer.parseInt(level))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}