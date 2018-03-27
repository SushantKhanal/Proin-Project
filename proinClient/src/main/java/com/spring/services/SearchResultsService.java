package com.spring.services;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface SearchResultsService {
    List<String> findResults(String country, String searchTxt);
    List<String> getResults(String searchTxt);
}
