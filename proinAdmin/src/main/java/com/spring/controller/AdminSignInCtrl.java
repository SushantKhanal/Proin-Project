package com.spring.controller;

import com.spring.requestDTO.SignInInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdminSignInCtrl {


//    @Autowired
//    private SignInService signInService;

    //checks for the matching username and password
    @PostMapping("/adminLogIn/")
    public ResponseEntity<Void> adminLogIn(@RequestBody SignInInfo signInInfo) {
        String username = "admin";
        String password = "admin";

        if(signInInfo.getUsername().equals(username) & signInInfo.getPassword().equals(password)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
