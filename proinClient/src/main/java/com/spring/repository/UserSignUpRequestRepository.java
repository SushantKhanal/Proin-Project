package com.spring.repository;


import com.spring.model.UserSignUpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSignUpRequestRepository extends JpaRepository<UserSignUpRequest, Long> {
}
