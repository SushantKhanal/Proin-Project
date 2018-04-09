package com.spring.services;

import com.spring.model.NormalProfilePic;
import com.spring.model.Value;
import com.spring.responseDto.FollowingDto;

import java.math.BigInteger;
import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface NormalAccountService {
    NormalProfilePic getUserPpByUsername(String username);
    void addProfilePic(NormalProfilePic normalProfilePic);
    List<FollowingDto> getFollowingsData(String username);
    BigInteger updateValue();
}
