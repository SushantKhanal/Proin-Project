package com.spring.repository;

import com.spring.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
