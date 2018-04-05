package com.spring.services;

import com.spring.model.*;
import com.spring.requestDto.FavDto;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface ViewProAccountService {
    UserProfilePic getProProfilePic(String username);
    UserTags receiveTags(String username);
    List<UserExperience> getAllExperience(String username);
    List<UserAcademics> getAllAcademics(String username);
    List<UserReviews> getReviews(String username);
    void addFav(FavDto favDto);

    List<FavProUsers> getResults(String loggedInNormalUsername);
    void deleteFav(Long favId);
}
