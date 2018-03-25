package com.spring.repository;

import com.spring.model.UserExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserExperienceRepository extends JpaRepository<UserExperience, Long> {
    @Query("SELECT e from UserExperience e where e.username=:username")
    UserExperience getUserExperienceByUsername (@Param("username") String username); //getUserExperienceByUsername
}
