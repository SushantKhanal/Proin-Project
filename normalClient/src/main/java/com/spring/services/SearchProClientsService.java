package com.spring.services;

import com.spring.model.User;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface SearchProClientsService {
    List<String> findResults(String country, String searchTxt);
    List<String> getResults(String searchTxt);
    User getProUserProfile(String username);
}
