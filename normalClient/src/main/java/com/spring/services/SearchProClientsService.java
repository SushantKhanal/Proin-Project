package com.spring.services;

import com.spring.model.User;
import com.spring.responseDto.SearchResultUserInfo;
import com.spring.responseDto.SearchResults;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface SearchProClientsService {
    SearchResults findResults(String country, String searchTxt, Pageable pageable);
    SearchResults getResults(String searchTxt, Pageable pageable);
    User getProUserProfile(String username);
}
