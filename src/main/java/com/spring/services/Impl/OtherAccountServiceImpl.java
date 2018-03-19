package com.spring.services.Impl;

import com.spring.model.FavUsers;
import com.spring.repository.FavUsersRepository;
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
}
