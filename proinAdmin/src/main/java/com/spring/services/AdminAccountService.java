package com.spring.services;

import com.spring.model.*;

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
    void addUserSignUpRequestStatus(UserSignUpRequestStatus uSRS);
    void addUser(User user);
    void approveAdminRequest(String username);
    void rejectAdminRequest(String username);
    List<String> getAdminRequests();
}
