package com.spring.services;

import com.spring.model.User;
import com.spring.responseDto.SearchResultUserInfo;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface SearchProClientsService {
    List<SearchResultUserInfo> findResults(String country, String searchTxt);
    List<SearchResultUserInfo> getResults(String searchTxt);
    User getProUserProfile(String username);
}
