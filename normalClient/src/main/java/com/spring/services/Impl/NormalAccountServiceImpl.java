package com.spring.services.Impl;

import com.spring.model.NormalProfilePic;
import com.spring.repository.NormalProfilePicRepository;
import com.spring.services.NormalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Service
@Transactional
public class NormalAccountServiceImpl implements NormalAccountService{

    @Autowired
    NormalProfilePicRepository normalProfilePicRepository;

    @Override
    public NormalProfilePic getUserPpByUsername(String username){
        return normalProfilePicRepository.getNormalProfilePicByusername(username);
    }

    @Override
    public void addProfilePic(NormalProfilePic normalProfilePic){
        normalProfilePicRepository.saveAndFlush(normalProfilePic);
    }


}
