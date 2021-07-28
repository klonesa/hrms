package com.bayrak.hrms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum LanguageLevel {
    @JsonProperty("1")
    ONE(1),
    @JsonProperty("2")
    TWO(2),
    @JsonProperty("3")
    THREE(3),
    @JsonProperty("4")
    FOUR(4),
    @JsonProperty("5")
    FIVE(5);


    @JsonValue
    int value;

    LanguageLevel(int value) {
        this.value = value;
    }
}
