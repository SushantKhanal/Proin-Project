package com.spring.repository;

import com.spring.model.UserProfilePic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface UserProfilePicRepository extends JpaRepository<UserProfilePic, Long> {
    @Query("SELECT p from UserProfilePic p where p.username=:username")
    UserProfilePic getUserProfilePicByusername(@Param("username") String username); //getUserProfilePicByUsername
}
