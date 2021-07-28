package com.bayrak.hrms.dto.jobAdvertisement;

import lombok.Value;

@Value
public class CloseJobAdvertisementByEmployerDto {
    int employerId;
    int jobAdvertisementId;
}
