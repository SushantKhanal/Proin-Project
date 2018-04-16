package com.spring.repository;

import com.spring.model.UserDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface UserDocumentsRepository extends JpaRepository<UserDocuments, Long>{
}
