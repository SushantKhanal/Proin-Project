package com.spring.services;

import java.util.List;

import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.User;

import javax.annotation.Resource;

@Service
@Transactional
public class UserDatabaseServiceImpl implements UserDatabaseService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addUser(User p) {
        try {

            userRepository.saveAndFlush(p);
        }catch (Exception e){
            System.out.println("Exception "+e);
        }
    }

    @Override
    @Transactional
    public void updateUser(User p) {
        userRepository.saveAndFlush(p);

    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        userRepository.delete(id);
    }

}
