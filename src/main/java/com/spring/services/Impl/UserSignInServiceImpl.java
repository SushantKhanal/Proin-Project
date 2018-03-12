package com.spring.services.Impl;

import com.spring.model.User;
import com.spring.repository.UserRepository;
import com.spring.services.UserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

@Service
@Transactional
public class UserSignInServiceImpl implements UserSignInService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

}
