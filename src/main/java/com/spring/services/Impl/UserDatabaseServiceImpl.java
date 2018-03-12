package com.spring.services.Impl;

import java.util.List;

import com.spring.repository.UserRepository;
import com.spring.services.UserDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.User;

@Service
@Transactional
public class UserDatabaseServiceImpl implements UserDatabaseService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateUser(User p) {
        userRepository.saveAndFlush(p);

    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void removeUser(Long id) {
        userRepository.delete(id);
    }

}
