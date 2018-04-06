package com.spring.services.Impl;

import com.spring.model.*;
import com.spring.repository.*;
import com.spring.requestDto.FavDto;
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

    @Autowired
    NormalUserRepository normalUserRepository;

    @Autowired
    FavProUsersRepository favProUsersRepository;

    @Autowired
    NormalUserReviewsRepository normalUserReviewsRepository;

    @Override
    public UserProfilePic getProProfilePic(String username) {
        return userProfilePicRepository.getUserProfilePicByusername(username);
    }

    @Override
    public UserTags receiveTags(String username) {
        return userTagsRepository.getUserTagsByUsername(username);
    }

    @Override
    public List<UserExperience> getAllExperience(String username){
        return userExperienceRepository.getUserExperienceByUsername(username);
    }

    @Override
    public List<UserAcademics> getAllAcademics(String username) {
        return userAcademicsRepository.getUserAcademicsByUsername(username);
    }

    @Override
    public List<NormalUserReviews> getReviews(String username) {
        Query query = em.createQuery("SELECT p from NormalUserReviews p where p.otherUsername like :otherUsername");
        query.setParameter("otherUsername","%"+username+"%");

        System.out.println(query.toString());
        List<NormalUserReviews> results = query.getResultList();
        return results;
    }

    @Override
    public void addFav(FavDto favDto) {
        NormalUser normalUser = normalUserRepository.getUserByUsername(favDto.getLoggedInNormalUser());
        FavProUsers favProUser = new FavProUsers(favDto.getLoggedInNormalUser(), favDto.getFavProUser(), normalUser);
        favProUsersRepository.saveAndFlush(favProUser);
    }

    @Override
    public void deleteFav(Long favId) {
        favProUsersRepository.delete(favId);
    }

    @Override
    public List<FavProUsers> getResults(String loggedInNormalUsername) {
        Query query = em.createQuery("SELECT p from FavProUsers p where p.loggedInNormalUsername like '%"+loggedInNormalUsername+"%'");
        List<FavProUsers> results = query.getResultList();
        return results;
    }

    @Override
    public List<NormalUserReviews> getNormalReviews(String loggedInUsername) {
        Query query = em.createQuery("SELECT n from NormalUserReviews n where n.loggedInUsername like :loggedInUsername");
        query.setParameter("loggedInUsername","%"+loggedInUsername+"%");

        System.out.println(query.toString());
        List<NormalUserReviews> results = query.getResultList();
        return results;
    }

    @Override
    public void addNormalReview(NormalUserReviews normalUserReviews1){
        normalUserReviewsRepository.saveAndFlush(normalUserReviews1);
    }


}
