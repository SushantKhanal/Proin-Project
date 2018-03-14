package com.spring.controller;

import com.spring.model.User;
import com.spring.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    //------------------- Update a User, by the same user --------------------------------------------------------

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        accountService.updateUser(user);
        User currentUser = accountService.getUserById(user.getId());
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    //----------------When user posts profile picture-----------------------------------------------------//

    @PostMapping("/user/profilePic/{username}")
    public ResponseEntity<User> postProfilePic(@PathVariable("username") String username, @RequestBody String image) {
        System.out.println("Posting Profile Pic of " + username);
        System.out.println("Posting Profile Pic .." + image);

        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

    }

}
