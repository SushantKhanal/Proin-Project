package com.spring.repository;

        import com.spring.model.UserProfilePic;
        import com.spring.model.UserTags;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;


/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface UserTagsRepository extends JpaRepository<UserTags, Long> {
    @Query("SELECT p from UserTags p where p.username=:username")
    UserTags getUserTagsByUsername (@Param("username") String username);
}

