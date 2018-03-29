package com.spring.repository;

import com.spring.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

}
