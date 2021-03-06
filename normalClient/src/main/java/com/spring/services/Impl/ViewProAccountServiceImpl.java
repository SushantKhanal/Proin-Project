package com.spring.services.Impl;

import com.spring.model.*;
import com.spring.repository.*;
import com.spring.requestDto.CheckIfFollowedDto;
import com.spring.requestDto.FavDto;
import com.spring.requestDto.LoggedMessageDto;
import com.spring.responseDto.ProUserDocInfo;
import com.spring.responseDto.ReviewDto;
import com.spring.responseDto.ReviewInfo;
import com.spring.services.ViewProAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
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

    @Autowired
    NormalFollowRequestRepository normalFollowRequestRepository;

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
    public ReviewDto getReviews(String loggedInUsername) {
//        Query query = em.createQuery("SELECT p from NormalUserReviews p where p.otherUsername like :otherUsername");
//        query.setParameter("otherUsername","%"+username+"%");
//
//        System.out.println(query.toString());
//        List<NormalUserReviews> results = query.getResultList();
//        return results;

        Query query = em.createQuery("SELECT p from UserReviews p where p.otherUsername like :loggedInUsername");
        query.setParameter("loggedInUsername","%"+loggedInUsername+"%");
        int resultLength = query.getResultList().size();

        List<UserReviews> results = query.getResultList();
        List<ReviewInfo> reviewInfoList= new ArrayList<ReviewInfo>();
        for (UserReviews element : results) {
            ReviewInfo reviewInfo1 = new ReviewInfo(element.getLoggedInUsername(),loggedInUsername, element.getReview(), element.getRating());
            reviewInfoList.add(reviewInfo1);
        }
        Query query1 = em.createQuery("SELECT p from NormalUserReviews p where p.otherUsername like :loggedInUsername");
        query1.setParameter("loggedInUsername","%"+loggedInUsername+"%");
        resultLength = resultLength + query1.getResultList().size();

        List<NormalUserReviews> results1 = query1.getResultList();
        for (NormalUserReviews element : results1) {
            ReviewInfo reviewInfo2 = new ReviewInfo(element.getLoggedInUsername(),loggedInUsername, element.getReview(), element.getRating());
            reviewInfoList.add(reviewInfo2);
        }
        ReviewDto reviewDto = new ReviewDto(reviewInfoList, resultLength);
        return reviewDto;
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
    //follow request sent status = 0
    @Override
    public void registerFollowRequest(NormalFollowRequest normalFollowRequest) {
        normalFollowRequestRepository.saveAndFlush(normalFollowRequest);
    }
    //follow request accepted status = 1
    @Override
    public String checkIfFollowed(CheckIfFollowedDto checkIfFollowedDto) {
        String from = checkIfFollowedDto.getLoggedInUsername();
        String to = checkIfFollowedDto.getOtherUsername();
        String sql = "SELECT f.id FROM normal_follow_request_table f" +
                " WHERE f.fromNormalUsername = :from AND f.toProUsername = :to" +
                " AND f.status = :status";
        Object result;
        String followStatus;
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("from", from);
            query.setParameter("to", to);
            query.setParameter("status", 0);
            result = query.getSingleResult();
            followStatus = "pending";
        }catch(Exception e){
            try {
                Query query1 = em.createNativeQuery(sql);
                query1.setParameter("from", from);
                query1.setParameter("to", to);
                query1.setParameter("status", 1);
                result = query1.getSingleResult();
                followStatus = "accepted";
            }catch(Exception e1){
                try {
                    Query query1 = em.createNativeQuery(sql);
                    query1.setParameter("from", from);
                    query1.setParameter("to", to);
                    query1.setParameter("status", 3);
                    result = query1.getSingleResult();
                    followStatus = "pending";
                }catch(Exception e2){
                    followStatus = "noRequestFound";
                    return followStatus;
                }
                return followStatus;
            }
            return followStatus;
        }
            return followStatus;
    }

//unfollowed status = 2
    @Override
    public void unfollow(CheckIfFollowedDto checkIfFollowedDto) {

        String sql = "UPDATE normal_follow_request_table nfr" +
                " SET status = 2"+
                " WHERE nfr.fromNormalUsername = :from AND nfr.toProUsername = :to";
        Query query = em.createNativeQuery(sql);
        query.setParameter("from", checkIfFollowedDto.getLoggedInUsername());
        query.setParameter("to", checkIfFollowedDto.getOtherUsername());
        query.executeUpdate();
    }

    @Override
    public Long checkPastRequests(LoggedMessageDto followRequest) {
        String sql = "SELECT f.id FROM normal_follow_request_table f" +
                " WHERE f.fromNormalUsername = :from AND f.toProUsername = :to";
        Query query = em.createNativeQuery(sql);
        query.setParameter("from", followRequest.getFromNormalUsername());
        query.setParameter("to", followRequest.getToProUsername());
        BigInteger result = (BigInteger) query.getSingleResult();
        return result.longValue();
    }

    @Override
    public List<ProUserDocInfo> checkForUploadedDocs(String username) {
        String sql = "SELECT * FROM user_documents_table ud" +
                " WHERE ud.username = :username";
        Query query = em.createNativeQuery(sql);
        query.setParameter("username", username);
        //List<UserDocuments> results = (List<UserDocuments>) query.getResultList();
        List<Object[]> results = query.getResultList();
        List<ProUserDocInfo> userDocInfos = new ArrayList<>();
        for(Object[] element : results) {
            ProUserDocInfo prouserDocInfo = new ProUserDocInfo();
            prouserDocInfo.setId(Long.parseLong(element[0].toString()));
            prouserDocInfo.setDocPath(element[1].toString());
            userDocInfos.add(prouserDocInfo);
        }
        return userDocInfos;
    }

}
