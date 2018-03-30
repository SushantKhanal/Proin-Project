package com.spring.services.Impl;

import com.spring.model.User;
import com.spring.model.UserStatus;
import com.spring.repository.UserRepository;
import com.spring.repository.UserStatusRepository;
import com.spring.services.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

@Service
@Transactional
public class SignInServiceImpl implements SignInService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserStatusRepository userStatusRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public UserStatus getUserStatusByUsername(String username) {
        return userStatusRepository.getUserStatusByUsername(username);
    }

}
