package com.bayrak.hrms.dto.jobAdvertisement;

import lombok.Value;

import java.util.Date;

@Value
public class JobAdvertiesementDto {
        int id;
        String name,companyName,title;
        Integer openPositionNumber;
        Date createdDate,closeDate;
}
