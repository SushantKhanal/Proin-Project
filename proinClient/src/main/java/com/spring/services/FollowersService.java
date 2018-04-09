package com.spring.services;

import com.spring.responseDto.NormalInfo;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface FollowersService {
    List<NormalInfo> getNormalUserEmails(String username);
}
