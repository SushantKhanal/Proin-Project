package com.spring.services;

import com.spring.model.NormalProfilePic;
import com.spring.model.Value;

import java.math.BigInteger;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface NormalAccountService {
    NormalProfilePic getUserPpByUsername(String username);
    void addProfilePic(NormalProfilePic normalProfilePic);
    BigInteger updateValue();
}
