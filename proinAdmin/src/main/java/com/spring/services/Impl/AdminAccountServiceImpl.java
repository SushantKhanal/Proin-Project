package com.spring.services.Impl;

import com.spring.model.*;
import com.spring.repository.*;
import com.spring.responseDTO.SearchResults;
import com.spring.services.AdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private UserSignUpRequestStatusRepository userSignUpRequestStatusRepository;

    @Autowired
    private AdminProfilePicRepository adminProfilePicRepository;

    @Override
    public SearchResults getResults(String searchTxt, Integer status, Pageable pageable) {

        String sql = "SELECT u.username FROM users_table u" +
                " LEFT JOIN users_tags_table t ON u.id = t.user_id" +
                " LEFT JOIN user_status_table s ON u.id = s.user_id" +
                " WHERE (u.username LIKE :searchTxtLike OR u.firstName LIKE :searchTxtLike " +
                "OR u.email LIKE :searchTxtLike OR t.tags LIKE :searchTxtLike)" +
                " and s.status != :status";

        SearchResults searchResults = new SearchResults();

        List<String> results = new ArrayList<>();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("searchTxtLike", "%" + searchTxt + "%");
            query.setParameter("status", status);

            int noOfitems = query.getResultList().size();
            query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
            results = query.getResultList();

            searchResults.setNoOfItems(noOfitems);
            searchResults.setResults(results);

        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return searchResults;
    }

    @Override
    public SearchResults findResults(String country, String searchTxt, Integer status, Pageable pageable) {

        String sql = "SELECT u.username FROM users_table u" +
                " LEFT JOIN users_tags_table t ON u.id = t.user_id" +
                " LEFT JOIN user_status_table s ON u.id = s.user_id" +
                " WHERE u.nation = :country and (u.username LIKE :searchTxt OR u.firstName LIKE :searchTxt OR u.email LIKE :searchTxt OR t.tags LIKE :searchTxtLike)" +
                " and s.status != :status";

        SearchResults searchResults = new SearchResults();
        List<String> results = new ArrayList<>();

        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("country", country);
            query.setParameter("searchTxt", searchTxt);
            query.setParameter("searchTxtLike", "%" + searchTxt + "%");
            query.setParameter("status", status);
            int noOfitems = query.getResultList().size();
            query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());

            results = query.getResultList();
            searchResults.setNoOfItems(noOfitems);
            searchResults.setResults(results);

        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return searchResults;
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
        String sql = "SELECT u.username FROM user_signUp_request_table u" +
                " LEFT JOIN userSignUpRequest_status_table uS ON u.id = uS.userSignUpRequest_id" +
                " WHERE uS.status != 0";
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
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void addUserSignUpRequestStatus(UserSignUpRequestStatus uSRS) {
        userSignUpRequestStatusRepository.saveAndFlush(uSRS);
    }

    @Override
    public List<String> getAdminRequests() {
        String sql = "SELECT a.username FROM admins_table a" +
                " WHERE a.status = 0";
        //fetch the requests with status 0
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
    public void approveAdminRequest(String username) {
        //change admin status
        String sql = "UPDATE admins_table a" +
                " SET a.status = 1" +
                " WHERE a.username = :username";
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("username", username);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }

    //ADMIN STATUS 0 == NEITHER ACCEPTED NOR REJECTED
//ADMIN STATUS 1 == ACCEPTED
//ADMIN STATUS 2 == REJECTED
    @Override
    public void rejectAdminRequest(String username) {
        //change admin status
        String sql = "UPDATE admins_table a" +
                " SET a.status = 2" +
                " WHERE a.username = :username";
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("username", username);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }

    @Override
    public AdminProfilePic getAdminPpByUsername(String username) {
        String sql = "SELECT * FROM admin_profile_pic_table ap"+
                " WHERE ap.username = :username";
        //fetch the matching admin

        Query query = em.createNativeQuery(sql, AdminProfilePic.class);
        query.setParameter("username", username);
        AdminProfilePic adminProfilePic = new AdminProfilePic();
        try{
            adminProfilePic = (AdminProfilePic) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
        }

        return adminProfilePic;
    }

    @Override
    public void addProfilePic(AdminProfilePic adminProfilePic1) {
        adminProfilePicRepository.saveAndFlush(adminProfilePic1);
    }
}
