package com.bayrak.hrms.entity.dto.resume;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Builder
@EqualsAndHashCode(of = "name")
public class SchoolDto {

    private String name;
    private boolean isGraduated;
    private Date graduateDate;
}
