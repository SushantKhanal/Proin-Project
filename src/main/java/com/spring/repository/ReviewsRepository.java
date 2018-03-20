package com.spring.repository;

import com.spring.model.UserReviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<UserReviews, Long> {

}
