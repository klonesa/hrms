package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.JobAdvertisement;
import com.bayrak.hrms.dto.jobAdvertisement.JobAdvertiesementDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdvertiesementDao extends JpaRepository<JobAdvertisement, Integer> {

    List<JobAdvertisement> getAllByEmployer_Id(int employerId);

    @Query("SELECT new com.bayrak.hrms.dto.jobAdvertisement.JobAdvertiesementDto (j.id ,j.city.name,j.employer" +
            ".companyName,j" +
            ".JobTitle.title, j" +
            ".openPositionNumber" +
            ", j.createdDate, j.closeDate ) from JobAdvertisement j where j.isActive=true and j.employer" +
            ".companyName=:companyName")
    List<JobAdvertiesementDto> getAllByEmployer_CompanyName(String companyName);


    @Query("SELECT new com.bayrak.hrms.dto.jobAdvertisement.JobAdvertiesementDto (j.id ,j.city.name,j.employer.companyName,j" +
            ".JobTitle.title, j" +
            ".openPositionNumber" +
            ", j.createdDate, j.closeDate ) from JobAdvertisement j where j.isActive=true ")
    List<JobAdvertiesementDto> getAllByActiveAdvertisements();

    @Query("SELECT new com.bayrak.hrms.dto.jobAdvertisement.JobAdvertiesementDto (j.id ,j.city.name,j.employer.companyName,j" +
            ".JobTitle.title, j" +
            ".openPositionNumber" +
            ", j.createdDate, j.closeDate ) from JobAdvertisement j where j.isActive=true order by j.createdDate desc ")
    List<JobAdvertiesementDto> getAllBySortedByDateActiveAdvertisements();

}
