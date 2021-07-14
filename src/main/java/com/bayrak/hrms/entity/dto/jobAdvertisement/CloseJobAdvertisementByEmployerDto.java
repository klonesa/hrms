package com.bayrak.hrms.entity.dto.jobAdvertisement;

import lombok.Value;

@Value
public class CloseJobAdvertisementByEmployerDto {
    int employerId;
    int jobAdvertisementId;
}
