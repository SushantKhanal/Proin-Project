package com.spring.services;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface AdminAccountService {
    List<String> getResults(String searchTxt);
    List<String> findResults(String country, String searchTxt);
}
