package com.spring.repository;

        import com.spring.model.UserSignUpRequestStatus;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

public interface UserSignUpRequestStatusRepository extends JpaRepository<UserSignUpRequestStatus, Long> {
    @Query("SELECT s from UserSignUpRequestStatus s where s.username=:username")
    UserSignUpRequestStatus getUserSignUpRequestStatusByUsername(@Param("username") String username);
}
