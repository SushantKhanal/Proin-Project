package com.spring.services.Impl;

import com.spring.model.UserProfilePic;
import com.spring.repository.UserProfilePicRepository;
import com.spring.repository.UserRepository;
import com.spring.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @PersistenceContext
    EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfilePicRepository userProfilePicRepository;

    @Override
    public void updateUser(User p) {
        userRepository.saveAndFlush(p);
    }

    @Override
    public void addProfilePic(UserProfilePic u_p) {
        try{
            userProfilePicRepository.saveAndFlush(u_p);
        }
        catch(Exception e){
            System.out.println("error");
        }
    }

    @Override
    public UserProfilePic getUserPpByUsername(String username){

        return userProfilePicRepository.getUserProfilePicByusername(username);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

}
