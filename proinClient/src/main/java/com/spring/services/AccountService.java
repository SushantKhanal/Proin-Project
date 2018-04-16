package com.spring.services;

import com.spring.model.*;

import java.util.List;

public interface AccountService {

    void updateUser(User p);

    User getUserById(Long id); //while updating user

    void addProfilePic(UserProfilePic u_p);

    UserProfilePic getUserPpByUsername(String username);

    void addUserTags(UserTags userTags1);

    UserTags getUserTagsByUsername(String username);

    void addUserExperience(UserExperience userExperience1);

    List<UserExperience> getUserExperienceByUsername(String username);

    List<UserAcademics> getUserAcademicsByUsername(String username);

    UserAcademics getUserAcademicsFromId(Long id);

    UserExperience getExperienceFromId(Long id);

    void addUserAcademics(UserAcademics userAcademics);

    void deleteThisAcademics(Long id);

    void deleteThisExperience(Long id);

    List<NormalFollowRequest> checkFollowRequests(String username);

    void approveFollowRequest(Long id);

    void ignoreFollowRequest(Long id);

    List<NormalFollowRequest> getIgnoredRequests();

    void saveUserDoc(UserDocuments userDocuments);

    void checkForUploadedDocs(String username);
}
