package com.spring.services.Impl;

import com.spring.model.*;
import com.spring.repository.*;
import com.spring.services.ViewProAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Service
@Transactional
public class ViewProAccountServiceImpl implements ViewProAccountService{

    @PersistenceContext
    EntityManager em;

    @Autowired
    UserTagsRepository userTagsRepository;

    @Autowired
    UserProfilePicRepository userProfilePicRepository;

    @Autowired
    UserAcademicsRepository userAcademicsRepository;

    @Autowired
    UserExperienceRepository userExperienceRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    public UserProfilePic getProProfilePic(String username) {
        return userProfilePicRepository.getUserProfilePicByusername(username);
    }

    public UserTags receiveTags(String username) {
        return userTagsRepository.getUserTagsByUsername(username);
    }

    public List<UserExperience> getAllExperience(String username){
        return userExperienceRepository.getUserExperienceByUsername(username);
    }

    public List<UserAcademics> getAllAcademics(String username) {
        return userAcademicsRepository.getUserAcademicsByUsername(username);
    }

    public List<UserReviews> getReviews(String username) {
        Query query = em.createQuery("SELECT p from UserReviews p where p.otherUsername like :otherUsername");
        query.setParameter("otherUsername","%"+username+"%");

        System.out.println(query.toString());
        List<UserReviews> results = query.getResultList();
        return results;
    }


}
