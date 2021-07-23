package com.bayrak.hrms.entity.dto.resume;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Builder
@EqualsAndHashCode
public class JobExperienceDto {
    private String CompanyName;
    private String jobTitle;
    private Date startDate;
    private Date endDate;
}
