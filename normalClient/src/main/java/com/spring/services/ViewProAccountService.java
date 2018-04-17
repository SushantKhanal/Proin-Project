package com.spring.services;

import com.spring.model.*;
import com.spring.requestDto.CheckIfFollowedDto;
import com.spring.requestDto.FavDto;
import com.spring.requestDto.LoggedMessageDto;
import com.spring.responseDto.ProUserDocInfo;

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
    List<NormalUserReviews> getReviews(String username);
    void addFav(FavDto favDto);
    List<FavProUsers> getResults(String loggedInNormalUsername);
    void deleteFav(Long favId);
    List<NormalUserReviews> getNormalReviews(String loggedInUsername);
    void addNormalReview(NormalUserReviews normalUserReviews1);
    void registerFollowRequest(NormalFollowRequest normalFollowRequest);
    String checkIfFollowed(CheckIfFollowedDto checkIfFollowedDto);
    void unfollow(CheckIfFollowedDto checkIfFollowedDto);
    Long checkPastRequests(LoggedMessageDto followRequest);
    List<ProUserDocInfo> checkForUploadedDocs(String username);
}
