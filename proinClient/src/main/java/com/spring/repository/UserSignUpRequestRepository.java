package com.spring.repository;


import com.spring.model.UserSignUpRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserSignUpRequestRepository extends JpaRepository<UserSignUpRequest, Long> {
    @Query("SELECT s from UserSignUpRequest s where s.username=:username")
    UserSignUpRequest getUserSignUpRequestByUsername(@Param("username") String username);
}
