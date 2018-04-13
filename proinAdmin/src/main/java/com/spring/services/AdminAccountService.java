package com.spring.services;

import com.spring.model.*;
import com.spring.responseDTO.SearchResults;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface AdminAccountService {
    SearchResults getResults(String searchTxt, Integer status, Pageable pageable);
    SearchResults findResults(String country, String searchTxt, Integer status, Pageable pageable);
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
