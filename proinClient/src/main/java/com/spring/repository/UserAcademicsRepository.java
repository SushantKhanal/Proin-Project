
package com.spring.repository;

        import com.spring.model.UserAcademics;
        import com.spring.model.UserExperience;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

        import java.util.List;

public interface UserAcademicsRepository extends JpaRepository<UserAcademics, Long> {
    @Query("SELECT e from UserAcademics e where e.username=:username")
    List<UserAcademics> getUserAcademicsByUsername(@Param("username") String username); //getUserAcademicsByUsername
}

