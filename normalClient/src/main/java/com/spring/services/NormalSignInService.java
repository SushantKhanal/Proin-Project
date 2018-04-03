package com.spring.services;

import com.spring.model.NormalUser;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
public interface NormalSignInService {
    NormalUser getNormalUserByUsername(String username);
}
