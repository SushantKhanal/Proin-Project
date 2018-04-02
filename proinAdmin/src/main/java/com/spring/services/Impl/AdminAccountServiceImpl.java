package com.spring.services.Impl;

import com.spring.model.User;
import com.spring.model.UserSignUpRequest;
import com.spring.model.UserStatus;
import com.spring.repository.UserRepository;
import com.spring.repository.UserSignUpRequestRepository;
import com.spring.repository.UserStatusRepository;
import com.spring.services.AdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class AdminAccountServiceImpl implements AdminAccountService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStatusRepository userStatusRepository;

    @Autowired
    private UserSignUpRequestRepository userSignUpRequestRepository;

    @Override
    public List<String> getResults(String searchTxt, Integer status) {

        String sql = "SELECT u.username FROM users_table u" +
                " LEFT JOIN users_tags_table t ON u.id = t.user_id" +
                " LEFT JOIN user_status_table s ON u.id = s.user_id" +
                " WHERE (u.username LIKE :searchTxtLike OR u.firstName LIKE :searchTxtLike " +
                "OR u.email LIKE :searchTxtLike OR t.tags LIKE :searchTxtLike)" +
                " and s.status != :status";

        List<String> results=new ArrayList<>();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("searchTxtLike", "%"+searchTxt+"%");
            query.setParameter("status", status);

            results=query.getResultList();

        }catch(Exception e){
            System.out.println("Exception "+e);
        }
        return results;
    }

    @Override
    public List<String> findResults(String country, String searchTxt, Integer status) {

        String sql = "SELECT u.username FROM users_table u" +
                " LEFT JOIN users_tags_table t ON u.id = t.user_id" +
                " LEFT JOIN user_status_table s ON u.id = s.user_id" +
                " WHERE u.nation = :country and (u.username LIKE :searchTxt OR u.firstName LIKE :searchTxt OR u.email LIKE :searchTxt OR t.tags LIKE :searchTxtLike)"+
                " and s.status != :status";


        List<String> results = new ArrayList<>();

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("country", country);
            query.setParameter("searchTxt", searchTxt);
            query.setParameter("searchTxtLike", "%" + searchTxt + "%");
            query.setParameter("status", status);

            results = query.getResultList();

        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return results;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public UserStatus getUserStatusByUsername(String username) {
        return userStatusRepository.getUserStatusByUsername(username);
    }

    @Override
    public List<String> getAllSignUpRequestUsernames() {
        String sql = "SELECT u.username FROM user_signUp_request_table u";
        //filter the requests with status 0
        List<String> results = new ArrayList<>();

        try {
            Query query = em.createNativeQuery(sql);
            results = query.getResultList();
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return results;
    }
    @Override
    public UserSignUpRequest getSignUpRequestByUsername(String username) {
        return userSignUpRequestRepository.getSignUpRequestByUsername(username);
    }

    @Override
    public void addUser(User user){
        userRepository.saveAndFlush(user);
    }

}
