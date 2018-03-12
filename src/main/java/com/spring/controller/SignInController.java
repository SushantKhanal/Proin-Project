package com.spring.controller;

import com.spring.dto.LoginRequestDTO;
import com.spring.model.User;
import com.spring.services.UserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SignInController {


    @Autowired
    private UserSignInService userSignInService; //data manipulation in the database

    //DTO -> Data transfer object
    //RequestDTO
    //ResponseDTO


    @PostMapping("/userLogIn/")
    public ResponseEntity<User> matchUser(@RequestBody LoginRequestDTO loginRequestDTO) {

        String username = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();
        User returnedUser = userSignInService.getUserByUsername(username);
        String returnedPassword = returnedUser.getPassword();

        if (returnedPassword.equals(password)) {
            return new ResponseEntity<User>(returnedUser, HttpStatus.OK);
        }

        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

}

