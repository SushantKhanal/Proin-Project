package com.spring.services.Impl;

import com.spring.model.User;
import com.spring.repository.UserRepository;
import com.spring.services.SearchProClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class SearchProClientsServiceImpl implements SearchProClientsService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<String> findResults(String country, String searchTxt) {


        String sql = "SELECT u.username FROM users_table u" +
                " LEFT JOIN users_tags_table t ON u.id = t.user_id" +
                " LEFT JOIN user_status_table s ON u.id = s.user_id" +
                " WHERE u.nation = :country and (u.username LIKE :searchTxt OR u.firstName LIKE :searchTxt OR u.email LIKE :searchTxt OR t.tags LIKE :searchTxtLike)"+
                " and s.status != 0";
        List<String> results=new ArrayList<>();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("country", country);
            query.setParameter("searchTxt", "%"+searchTxt+"%");
            query.setParameter("searchTxtLike", "%" + searchTxt + "%");

            results = query.getResultList();

        }catch(Exception e){
            System.out.println("Exception "+e);
        }
        return results;
    }

    @Override
    public List<String> getResults(String searchTxt) {

        String sql = "SELECT u.username FROM users_table u" +
                " LEFT JOIN users_tags_table t ON u.id = t.user_id" +
                " LEFT JOIN user_status_table s ON u.id = s.user_id" +
                " WHERE (u.username LIKE :searchTxtLike OR u.firstName LIKE :searchTxtLike " +
                "OR u.email LIKE :searchTxtLike OR t.tags LIKE :searchTxtLike)" +
                " and s.status != 0";

        List<String> results=new ArrayList<>();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("searchTxtLike", "%"+searchTxt+"%");

            results=query.getResultList();

        }catch(Exception e){
            System.out.println("Exception "+e);
        }
        return results;
    }

    @Override
    public User getProUserProfile(String username) {
        return userRepository.getUserByUsername(username);
    }

}
