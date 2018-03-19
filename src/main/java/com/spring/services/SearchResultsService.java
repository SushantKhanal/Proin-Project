package com.spring.services;

import com.spring.model.User;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface SearchResultsService {
    List<User> findResults(String country, String searchTxt);
}