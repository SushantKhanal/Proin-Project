package com.spring.services.Impl;

import com.spring.model.User;
import com.spring.repository.UserRepository;
import com.spring.responseDto.SearchResultUserInfo;
import com.spring.responseDto.SearchResults;
import com.spring.services.SearchProClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public SearchResults findResults(String country, String searchTxt, Pageable pageable) {

        String sql = "SELECT u.username, t.tags FROM users_table u" +
                " LEFT JOIN users_tags_table t ON u.id = t.user_id" +
                " LEFT JOIN user_status_table s ON u.id = s.user_id" +
                " WHERE u.nation = :country and (u.username LIKE :searchTxt OR u.firstName LIKE :searchTxt OR u.email LIKE :searchTxt OR t.tags LIKE :searchTxtLike)"+
                " and s.status != 0";
        List<Object[]> results=new ArrayList<>();
        List<SearchResultUserInfo> searchResultUserInfos = new ArrayList<>();
        int noOfitems;
        SearchResults searchResults = null;
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("country", country);
            query.setParameter("searchTxt", "%"+searchTxt+"%");
            query.setParameter("searchTxtLike", "%" + searchTxt + "%");
            noOfitems = query.getResultList().size();
            query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());

            results = query.getResultList();
            for(Object[] element : results) {
                if(element[1] == null) {
                    element[1] = "no tags to show";
                }
                SearchResultUserInfo searchResultUserInfo = new SearchResultUserInfo(element[0].toString(), element[1].toString());
                searchResultUserInfos.add(searchResultUserInfo);
            }
            searchResults = new SearchResults(searchResultUserInfos, noOfitems);

        }catch(Exception e){
            System.out.println("Exception "+e);
        }
        return searchResults;
    }

    @Override
    public SearchResults getResults(String searchTxt, Pageable pageable) {

        String sql = "SELECT u.username, t.tags FROM users_table u" +
                " LEFT JOIN users_tags_table t ON u.id = t.user_id" +
                " LEFT JOIN user_status_table s ON u.id = s.user_id" +
                " WHERE (u.username LIKE :searchTxtLike OR u.firstName LIKE :searchTxtLike " +
                "OR u.email LIKE :searchTxtLike OR t.tags LIKE :searchTxtLike)" +
                " and s.status != 0";

        List<Object[]> results=new ArrayList<>();
        List<SearchResultUserInfo> searchResultUserInfos = new ArrayList<>();
        SearchResults searchResults = new SearchResults();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("searchTxtLike", "%"+searchTxt+"%");
            int noOfitems = query.getResultList().size();
            query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());

            results=query.getResultList();
            for(Object[] element : results) {
                if(element[1] == null) {
                    element[1] = "no tags to show";
                }
                SearchResultUserInfo searchResultUserInfo = new SearchResultUserInfo(element[0].toString(), element[1].toString());
                searchResultUserInfos.add(searchResultUserInfo);
            }
            searchResults.setResults(searchResultUserInfos);
            searchResults.setNoOfItems(noOfitems);
        }catch(Exception e){
            System.out.println("Exception "+e);
        }
        return searchResults;
    }

    @Override
    public User getProUserProfile(String username) {
        return userRepository.getUserByUsername(username);
    }

}
