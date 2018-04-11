package com.spring.controller;

import com.spring.model.Admin;
import com.spring.requestDTO.SignInInfo;
import com.spring.services.AdminSignInService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdminSignInCtrl {
    private final AdminSignInService adminSignInService;

    public AdminSignInCtrl(AdminSignInService adminSignInService) {
        this.adminSignInService = adminSignInService;
    }

    //CHECKS FOR THE MATCHING USERNAME AND PASSWORD
    @PostMapping("/adminLogIn/")
    public ResponseEntity<Admin> adminLogIn(@RequestBody SignInInfo signInInfo) {

        if(signInInfo.getUsername().equals("admin") & signInInfo.getPassword().equals("admin")) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            try {
                Admin admin = adminSignInService.confirmAdminSignIn(signInInfo);
                return new ResponseEntity<>(admin, HttpStatus.OK);
            } catch(Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

    }
}
