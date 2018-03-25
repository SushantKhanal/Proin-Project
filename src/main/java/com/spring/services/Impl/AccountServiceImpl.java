package com.spring.services.Impl;

import com.spring.model.UserExperience;
import com.spring.model.UserProfilePic;
import com.spring.model.UserTags;
import com.spring.repository.UserProfilePicRepository;
import com.spring.repository.UserRepository;
import com.spring.repository.UserTagsRepository;
import com.spring.repository.UserExperienceRepository;
import com.spring.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @PersistenceContext
    EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfilePicRepository userProfilePicRepository;

    @Autowired
    private UserTagsRepository userTagsRepository;

    @Autowired
    private UserExperienceRepository userExperienceRepository;

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

    @Override
    public void addUserTags(UserTags userTags1) {
        try{
            userTagsRepository.saveAndFlush(userTags1);
        }
        catch(Exception e){
            System.out.println("error");
        }
    }

    @Override
    public UserTags getUserTagsByUsername(String username){
        return userTagsRepository.getUserTagsByUsername(username);
    }

    @Override
    public void addUserExperience(UserExperience userExperience1) {
        try{
            userExperienceRepository.saveAndFlush(userExperience1);
        }
        catch(Exception e){
            System.out.println("error");
        }
    }

    @Override
    public UserExperience getUserExperienceByUsername(String username){
        return userExperienceRepository.getUserExperienceByUsername(username);
    }

}
