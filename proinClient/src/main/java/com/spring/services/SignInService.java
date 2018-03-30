package com.spring.services;

import com.spring.model.User;
import com.spring.model.UserStatus;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface SignInService {
    User getUserByUsername(String username); //signin
    UserStatus getUserStatusByUsername(String username);
}
