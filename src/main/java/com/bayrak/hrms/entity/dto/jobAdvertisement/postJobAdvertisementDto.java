package com.bayrak.hrms.entity.dto.jobAdvertisement;

import lombok.Value;

import java.util.Date;

@Value
public class postJobAdvertisementDto {
    int  jobTitleId;
    int cityId;
    int employerId;
    String description;
    int minSalary;
    int maxSalary;
    int openPositionNumber;
    Date createdDate;
    Date closeDate;
}
