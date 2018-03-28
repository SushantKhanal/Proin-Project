package com.spring.services.Impl;

import com.spring.services.AdminAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Service
@Transactional
public class AdminAccountServiceImpl implements AdminAccountService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<String> getResults(String searchTxt) {
        String sql = "SELECT u.username FROM users_table u" +
                " JOIN users_tags_table t ON u.id = t.user_id" +
                " WHERE u.username LIKE :searchTxtLike OR u.firstName LIKE :searchTxtLike OR u.email LIKE :searchTxtLike OR t.tags LIKE :searchTxtLike";
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
    public List<String> findResults(String country, String searchTxt) {
        String sql = "SELECT u.username FROM users_table u" +
                " JOIN users_tags_table t ON u.id = t.user_id" +
                " WHERE u.nation = :country and (u.username = :searchTxt OR u.firstName = :searchTxt OR u.email = :searchTxt OR t.tags LIKE :searchTxtLike)";
        List<String> results = new ArrayList<>();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("country", country);
            query.setParameter("searchTxt", searchTxt);
            query.setParameter("searchTxtLike", "%" + searchTxt + "%");

            results = query.getResultList();

        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return results;
    }
}
