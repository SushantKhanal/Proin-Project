package com.spring.services.Impl;

import com.spring.model.NormalProfilePic;
import com.spring.repository.NormalProfilePicRepository;
import com.spring.repository.ValueRepository;
import com.spring.services.NormalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;


/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Service
@Transactional
public class NormalAccountServiceImpl implements NormalAccountService{

    @PersistenceContext
    EntityManager em;

    @Autowired
    NormalProfilePicRepository normalProfilePicRepository;

    @Autowired
    ValueRepository valueRepository;

    @Override
    public NormalProfilePic getUserPpByUsername(String username){
        return normalProfilePicRepository.getNormalProfilePicByusername(username);
    }

    @Override
    public void addProfilePic(NormalProfilePic normalProfilePic){
        normalProfilePicRepository.saveAndFlush(normalProfilePic);
    }

    @Override
    public BigInteger updateValue() {
//        UPDATE Value_table
//        SET value = LAST_INSERT_ID(value) + 1
//        WHERE id = 1;
//
//        SELECT LAST_INSERT_ID();
        String sql = "UPDATE Value_table u" +
                " SET value = LAST_INSERT_ID(value) + 1"+
                " WHERE id = 1";

        BigInteger integer = null;
        try {
            Query query1 = em.createNativeQuery(sql);

            query1.executeUpdate();

            Query query = em.createNativeQuery("SELECT LAST_INSERT_ID()");

            integer= (BigInteger) query.getSingleResult();


        }catch(Exception e){
            System.out.println("Exception "+e);
        }
        return integer;
    }

}
