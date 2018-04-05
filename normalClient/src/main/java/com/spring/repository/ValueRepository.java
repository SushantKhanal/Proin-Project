package com.spring.repository;

import com.spring.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface ValueRepository extends JpaRepository<Value, Long> {
}
