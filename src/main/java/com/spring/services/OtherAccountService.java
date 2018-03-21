package com.spring.services;

import com.spring.model.FavUsers;
import com.spring.model.UserReviews;

import java.util.List;

public interface OtherAccountService {
    void addFavouriteUser(FavUsers favUsers1);
    List<FavUsers> getResults(String loggedInUsername);
    void deleteFav(Long favId);
    void addReview(UserReviews UserReviews1);
    List<UserReviews> getReviewRecords(String otherUsername);
    List<UserReviews> getAllReviews(String loggedInUsername);
}
