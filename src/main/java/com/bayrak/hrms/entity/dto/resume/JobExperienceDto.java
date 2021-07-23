package com.bayrak.hrms.entity.dto.resume;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
