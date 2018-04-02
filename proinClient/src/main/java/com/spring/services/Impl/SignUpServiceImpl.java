package com.spring.services.Impl;

import com.spring.model.UserSignUpRequest;
import com.spring.model.UserSignUpRequestStatus;
import com.spring.repository.UserSignUpRequestRepository;
import com.spring.repository.UserSignUpRequestStatusRepository;
import com.spring.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserSignUpRequestRepository userSignUpRequestRepository;

    @Autowired
    private UserSignUpRequestStatusRepository userSignUpRequestStatusRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    public void addUserSignUpRequest(UserSignUpRequest userSignUpRequest) {
        userSignUpRequestRepository.saveAndFlush(userSignUpRequest);
    }

    @Override
    public void addUserSignUpRequestStatus(UserSignUpRequestStatus uSRS) {
        userSignUpRequestStatusRepository.saveAndFlush(uSRS);
    }

    @Override
    public UserSignUpRequest getUserSignUpRequestByUsername(String username) {
        return userSignUpRequestRepository.getUserSignUpRequestByUsername(username);
    }
}
