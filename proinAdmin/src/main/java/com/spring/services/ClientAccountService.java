package com.spring.services;

import com.spring.model.*;

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

    List<UserReviews> getAllReviews(String loggedInUsername);

    List<FavUsers> getResults(String loggedInUsername);

    void addUserStatus(UserStatus userStatus);
}
