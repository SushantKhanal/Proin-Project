package com.spring.repository;

import com.spring.model.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface NormalUserRepository extends JpaRepository<NormalUser, Long> {
    NormalUser getUserByUsername(String username);
}
