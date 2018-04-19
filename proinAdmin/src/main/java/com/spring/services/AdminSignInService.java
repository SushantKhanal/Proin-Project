package com.spring.services;

import com.spring.model.Admin;
import com.spring.requestDTO.SignInInfo;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

public interface AdminSignInService {
    Admin confirmAdminSignIn(SignInInfo signInInfo);
    Admin getAdminByUsername(String username);
}
