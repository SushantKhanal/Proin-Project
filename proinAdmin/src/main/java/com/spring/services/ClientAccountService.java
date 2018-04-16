package com.spring.services;

import com.spring.model.*;
import com.spring.responseDTO.ReviewDto;
import com.spring.responseDTO.ReviewInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface ClientAccountService {
    UserProfilePic getUserPpByUsername(String username);

    UserTags getUserTagsByUsername(String username);

    List<UserExperience> getUserExperienceByUsername(String username);

    List<UserAcademics> getUserAcademicsByUsername(String username);

    ReviewDto getAllReviews(String loggedInUsername, Pageable pageable);

    List<FavUsers> getResults(String loggedInUsername);

    void addUserStatus(UserStatus userStatus);
}
