package com.spring.controller;

import com.spring.model.User;
import com.spring.services.SignInService;
import com.spring.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private SignInService signInService;


    //SignUpController
    //-------------------Create a User, SignUp--------------------------------------------------------

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("Creating User " + user.getUsername());

        String providedUsername = user.getUsername();

        signUpService.addUser(user); //added to the database

        User returnedUser = signInService.getUserByUsername(providedUsername);

        return new ResponseEntity<User>(returnedUser, HttpStatus.OK);

    }


}