package com.spring.services.Impl;

import com.spring.responseDto.NormalInfo;
import com.spring.services.FollowersService;
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
public class FollowersServiceImpl implements FollowersService{
    @PersistenceContext
    EntityManager em;
    @Override
    public List<NormalInfo> getNormalUserEmails(String username){

        String sql = "SELECT f.fromNormalUsername FROM normal_follow_request_table f" +
                " WHERE f.status = 1 and f.toProUsername = :username";
        List<String> results = new ArrayList<>();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("username", username);
            results = query.getResultList();
        }catch(Exception e){
            System.out.println("Exception "+e);
        }
        List<NormalInfo> normalInfoList = new ArrayList<NormalInfo>();
        for (String normalUsername : results) {
            String sql1 = "SELECT n.email FROM normal_users_table n" +
                    " WHERE n.username = :normalUsername";
            Query query1 = em.createNativeQuery(sql1);
            query1.setParameter("normalUsername", normalUsername);
            String result = (String) query1.getSingleResult();
            NormalInfo normalInfo = new NormalInfo(normalUsername, result);
            normalInfoList.add(normalInfo);
        }
        return normalInfoList;
    }

}
