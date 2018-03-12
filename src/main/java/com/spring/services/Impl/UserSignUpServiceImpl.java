package com.spring.services.Impl;

import com.spring.model.User;
import com.spring.repository.UserRepository;
import com.spring.services.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

@Service
@Transactional
public class UserSignUpServiceImpl implements UserSignUpService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User p) {
        try {

            userRepository.saveAndFlush(p);
        }catch (Exception e){
            System.out.println("Exception "+e);
        }
    }
}
