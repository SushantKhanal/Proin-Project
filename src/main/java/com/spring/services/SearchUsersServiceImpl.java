package com.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Service("searchUsersService")
public class SearchUsersServiceImpl implements SearchUsersService {
    @Autowired
    UserService userService;

}
