package com.spring.services.Impl;

import com.spring.repository.UserRepository;
import com.spring.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.User;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateUser(User p) {
        userRepository.saveAndFlush(p);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

}