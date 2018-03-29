package com.spring.services.Impl;

import com.spring.model.*;
import com.spring.repository.*;
import com.spring.services.ClientAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Service
@Transactional
public class ClientAccountServiceImpl implements ClientAccountService {
    @PersistenceContext
    EntityManager em;

    @Autowired
    private UserProfilePicRepository userProfilePicRepository;

    @Autowired
    private UserTagsRepository userTagsRepository;

    @Autowired
    private UserExperienceRepository userExperienceRepository;

    @Autowired
    private UserStatusRepository userStatusRepository;


    @Autowired
    private UserAcademicsRepository userAcademicsRepository;

    @Override
    public UserProfilePic getUserPpByUsername(String username){

        return userProfilePicRepository.getUserProfilePicByusername(username);

    }

    @Override
    public UserTags getUserTagsByUsername(String username){
        return userTagsRepository.getUserTagsByUsername(username);
    }

    @Override
    public List<UserExperience> getUserExperienceByUsername(String username){
        return userExperienceRepository.getUserExperienceByUsername(username);
    }

    @Override
    public List<UserAcademics> getUserAcademicsByUsername(String username){
        List<UserAcademics> userAcademics = userAcademicsRepository.getUserAcademicsByUsername(username);
        return userAcademics;
    }

    @Override
    public List<FavUsers> getResults(String loggedInUsername) {
        Query query = em.createQuery("SELECT p from FavUsers p where p.loggedInUsername like '%"+loggedInUsername+"%'");
        List<FavUsers> results = query.getResultList();
        return results;
    }

    @Override
    public List<UserReviews> getAllReviews(String loggedInUsername) {
        Query query = em.createQuery("SELECT p from UserReviews p where p.loggedInUsername like :loggedInUsername");
        query.setParameter("loggedInUsername","%"+loggedInUsername+"%");

        System.out.println(query.toString());
        List<UserReviews> results = query.getResultList();
        return results;
    }

    @Override
    public void addUserStatus(UserStatus userStatus) {
        userStatusRepository.saveAndFlush(userStatus);
    }

}
