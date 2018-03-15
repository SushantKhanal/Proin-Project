package com.spring.services;

import com.spring.model.User;
import com.spring.model.UserProfilePic;

public interface AccountService {


    void updateUser(User p);

    User getUserById(Long id); //while updating user

    void addProfilePic(UserProfilePic u_p);

    UserProfilePic getUserPpByUsername(String username);

}
