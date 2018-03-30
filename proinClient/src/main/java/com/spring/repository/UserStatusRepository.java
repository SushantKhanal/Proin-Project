package com.spring.repository;

import com.spring.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    @Query("SELECT s from UserStatus s where s.username=:username")
    UserStatus getUserStatusByUsername(@Param("username") String username);
}
