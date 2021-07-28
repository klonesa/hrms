package com.bayrak.hrms.dto.resume;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Builder
@EqualsAndHashCode
public class JobExperienceDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String companyName;
    private String jobTitle;
    private Date startDate;
    private Date endDate;
}
