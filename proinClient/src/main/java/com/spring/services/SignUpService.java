package com.spring.services;

import com.spring.model.User;
import com.spring.model.UserSignUpRequest;
import com.spring.model.UserStatus;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface SignUpService {
    void addUser(User p); //signup
    void addUserStatus(UserStatus userStatus); //add status 1
    void addUserSignUpRequest(UserSignUpRequest p);
}
