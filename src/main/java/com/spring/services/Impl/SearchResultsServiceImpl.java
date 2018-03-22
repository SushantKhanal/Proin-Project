package com.spring.services.Impl;

import com.spring.model.User;
import com.spring.services.SearchResultsService;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Service("searchUsersService")
@Transactional
public class SearchResultsServiceImpl implements SearchResultsService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<User> findResults(String country, String searchTxt) {
        //Query query = em.createQuery("SELECT p from User p where p.nation like '%"+country+"%'");
        //List<User> results = query.getResultList();
        //return results;

        //String sql = "SELECT * FROM User WHERE nation = :country";
        String sql = "SELECT * FROM users_table WHERE nation = :country and (username = :searchTxt OR firstName = :searchTxt OR email = :searchTxt)";
        Query query = em.createNativeQuery(sql, User.class).setParameter("country", country).setParameter("searchTxt", searchTxt);
        List<User> results = query.getResultList();
        return results;
    }

}
