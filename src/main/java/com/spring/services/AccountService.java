package com.spring.services;

import com.spring.model.*;

public interface AccountService {


    void updateUser(User p);

    User getUserById(Long id); //while updating user

    void addProfilePic(UserProfilePic u_p);

    UserProfilePic getUserPpByUsername(String username);

    void addUserTags(UserTags userTags1);

    UserTags getUserTagsByUsername(String username);

    void addUserExperience(UserExperience userExperience1);

    UserExperience getUserExperienceByUsername(String username);

    UserAcademics getUserAcademicsByUsername(String username);

    void addUserAcademics(UserAcademics userAcademics);

}
