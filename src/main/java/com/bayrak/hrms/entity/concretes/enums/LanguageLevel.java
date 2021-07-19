package com.bayrak.hrms.entity.concretes.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum LanguageLevel {
    ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5);

    @JsonValue
    int value;

    LanguageLevel(int value) {
        this.value = value;
    }
}
