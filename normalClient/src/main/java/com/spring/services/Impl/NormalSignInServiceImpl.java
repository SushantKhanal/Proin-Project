package com.spring.services.Impl;

import com.spring.model.NormalUser;
import com.spring.repository.NormalUserRepository;
import com.spring.services.NormalSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Service
@Transactional
public class NormalSignInServiceImpl implements NormalSignInService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private NormalUserRepository normalUserRepository;

    @Override
    public NormalUser getNormalUserByUsername(String username) {
        return normalUserRepository.getUserByUsername(username);
    }

}
