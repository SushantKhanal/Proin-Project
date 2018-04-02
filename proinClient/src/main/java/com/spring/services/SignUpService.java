package com.spring.services;

import com.spring.model.User;
import com.spring.model.UserSignUpRequest;
import com.spring.model.UserSignUpRequestStatus;
import com.spring.model.UserStatus;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface SignUpService {
    void addUserSignUpRequest(UserSignUpRequest p);
    void addUserSignUpRequestStatus(UserSignUpRequestStatus uSRS);
    UserSignUpRequest getUserSignUpRequestByUsername(String username);
}
