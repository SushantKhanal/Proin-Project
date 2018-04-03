package com.spring.services;

import com.spring.model.NormalProfilePic;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface NormalAccountService {
    NormalProfilePic getUserPpByUsername(String username);
    void addProfilePic(NormalProfilePic normalProfilePic);
}
