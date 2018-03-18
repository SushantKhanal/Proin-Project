package com.spring.services.Impl;

import com.spring.model.FavUsers;
import com.spring.repository.FavUsersRepository;
import com.spring.services.OtherAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OtherAccountServiceImpl implements OtherAccountService {

    @Autowired
    private FavUsersRepository favUsersRepository;

    @Override
    public void addFavouriteUser(FavUsers favUsers1) {
        try{
            favUsersRepository.saveAndFlush(favUsers1);
        }
        catch(Exception e){
            System.out.println("error");
        }
    }
}
