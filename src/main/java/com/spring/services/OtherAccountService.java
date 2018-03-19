package com.spring.services;

import com.spring.model.FavUsers;

import java.util.List;

public interface OtherAccountService {
    void addFavouriteUser(FavUsers favUsers1);
    List<FavUsers> getResults(String loggedInUsername);
    void deleteFav(Long favId);
}
