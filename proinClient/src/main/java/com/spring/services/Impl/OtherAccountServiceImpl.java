package com.spring.services.Impl;

import com.spring.model.FavUsers;
import com.spring.model.UserReviews;
import com.spring.repository.FavUsersRepository;
import com.spring.repository.ReviewsRepository;
import com.spring.services.OtherAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Transactional
public class OtherAccountServiceImpl implements OtherAccountService {

    @Autowired
    private FavUsersRepository favUsersRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    private ReviewsRepository ReviewsRepository;

    @Override
    public void addFavouriteUser(FavUsers favUsers1) {
        try{
            favUsersRepository.saveAndFlush(favUsers1);
        }
        catch(Exception e){
            System.out.println("error");
        }
    }

    @Override
    public List<FavUsers> getResults(String loggedInUsername) {
        Query query = em.createQuery("SELECT p from FavUsers p where p.loggedInUsername like '%"+loggedInUsername+"%'");
        List<FavUsers> results = query.getResultList();
        return results;
    }

    @Override
    public void deleteFav(Long favId) {
        favUsersRepository.delete(favId);
    }

    @Override
    public void addReview(UserReviews UserReviews1) {
        try{
            ReviewsRepository.saveAndFlush(UserReviews1);
        }
        catch(Exception e){
            System.out.println("error");
        }
    }

    @Override
    public List<UserReviews> getReviewRecords(String otherUsername) {
        Query query = em.createQuery("SELECT p from UserReviews p where p.otherUsername like :otherUsername");
        query.setParameter("otherUsername","%"+otherUsername+"%");

        System.out.println(query.toString());
        List<UserReviews> results = query.getResultList();
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
}
