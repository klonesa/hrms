package com.bayrak.hrms.repository;

import com.bayrak.hrms.entity.concretes.ResumePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResumePhotoDao extends JpaRepository<ResumePhoto, Integer> {

    //@Modifying
    @Query(value = "insert into resume_photos (id, photo_url) values (:id, :photo_url)",
            nativeQuery = true)
    void insertUser(@Param("id") Integer resumeId, @Param("photo_url") String photoUrl);

}
