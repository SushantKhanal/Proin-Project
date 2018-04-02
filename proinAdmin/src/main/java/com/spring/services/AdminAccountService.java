package com.spring.services;

import com.spring.model.User;
import com.spring.model.UserSignUpRequest;
import com.spring.model.UserStatus;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface AdminAccountService {
    List<String> getResults(String searchTxt, Integer status);
    List<String> findResults(String country, String searchTxt, Integer status);
    User getUserByUsername(String username);
    UserStatus getUserStatusByUsername(String username);
    List<String> getAllSignUpRequestUsernames();
    UserSignUpRequest getSignUpRequestByUsername(String username);
    void addUser(User user);
}
