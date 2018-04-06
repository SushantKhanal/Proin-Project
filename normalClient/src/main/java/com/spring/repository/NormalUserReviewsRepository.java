package com.spring.repository;

import com.spring.model.NormalUserReviews;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface NormalUserReviewsRepository extends JpaRepository<NormalUserReviews, Long> {
}
