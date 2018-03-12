package com.spring.services;

import com.spring.model.User;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface UserSignInService {
    User getUserByUsername(String username); //signin
}
