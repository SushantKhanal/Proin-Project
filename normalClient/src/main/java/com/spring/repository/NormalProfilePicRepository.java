package com.spring.repository;

import com.spring.model.NormalProfilePic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface NormalProfilePicRepository extends JpaRepository<NormalProfilePic, Long> {
    @Query("SELECT p from NormalProfilePic p where p.username=:username")
    NormalProfilePic getNormalProfilePicByusername(@Param("username") String username); //getUserProfilePicByUsername
}
