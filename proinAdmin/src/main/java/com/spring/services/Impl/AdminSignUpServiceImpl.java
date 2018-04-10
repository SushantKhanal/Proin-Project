package com.spring.services.Impl;

import com.spring.model.Admin;
import com.spring.repository.AdminRepository;
import com.spring.services.AdminSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

@Service
@Transactional
public class AdminSignUpServiceImpl implements AdminSignUpService{
    @Autowired
    AdminRepository adminRepository;

    @Override
    public void createAdmin(Admin admin) {
        try{
            adminRepository.saveAndFlush(admin);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
