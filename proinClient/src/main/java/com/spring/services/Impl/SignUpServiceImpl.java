package com.spring.services.Impl;

import com.spring.model.User;
import com.spring.model.UserSignUpRequest;
import com.spring.model.UserStatus;
import com.spring.repository.UserRepository;
import com.spring.repository.UserSignUpRequestRepository;
import com.spring.repository.UserStatusRepository;
import com.spring.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStatusRepository userStatusRepository;

    @Autowired
    private UserSignUpRequestRepository userSignUpRequestRepository;

    @Override
    public void addUser(User p) {
        try {

            userRepository.saveAndFlush(p);
        }catch (Exception e){
            System.out.println("Exception "+e);
        }
    }

    @Override
    public void addUserStatus(UserStatus userStatus) {
        userStatusRepository.saveAndFlush(userStatus);
    }

    @Override
    public void addUserSignUpRequest(UserSignUpRequest userSignUpRequest) {
        userSignUpRequestRepository.saveAndFlush(userSignUpRequest);
    }
}
