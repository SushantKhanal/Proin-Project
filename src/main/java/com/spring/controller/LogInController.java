package com.spring.controller;

import com.spring.dto.LoginRequestDTO;
import com.spring.model.User;
import com.spring.services.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogInController {
    @Autowired
    private LogInService logInService;

    //DTO -> Data transfer object
    //RequestDTO
    //ResponseDTO


    @PostMapping("/userLogIn/")
    public ResponseEntity<User> matchUser(@RequestBody LoginRequestDTO loginRequestDTO) {

        List<User> users = logInService.findAllUsers();
        String username = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();
        User returnedUser = logInService.matchUser(username, password);

        if (users.contains(returnedUser)) {
            return new ResponseEntity<User>(returnedUser, HttpStatus.OK);
        }

        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

}

