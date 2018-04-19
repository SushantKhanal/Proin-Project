package com.spring.services.Impl;

import com.spring.model.Admin;
import com.spring.requestDTO.SignInInfo;
import com.spring.services.AdminSignInService;
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
public class AdminSignInServiceImpl implements AdminSignInService {
    @PersistenceContext
    EntityManager em;
    @Override
    public Admin confirmAdminSignIn(SignInInfo signInInfo) {

        String sql = "SELECT * FROM admins_table at"+
                " WHERE at.username = :username AND at.password = :password";
        //fetch the matching admin

        Query query = em.createNativeQuery(sql, Admin.class);
        query.setParameter("username", signInInfo.getUsername());
        query.setParameter("password", signInInfo.getPassword());
        Admin admin = (Admin) query.getSingleResult();

        return admin;
    }
    @Override
    public Admin getAdminByUsername(String username) {
        String sql = "SELECT * FROM admins_table at"+
                " WHERE at.username = :username";
        //fetch the matching admin

        Query query = em.createNativeQuery(sql, Admin.class);
        query.setParameter("username", username);
        Admin admin = (Admin) query.getSingleResult();

        return admin;
    }
}
