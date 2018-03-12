package com.spring.controller;

import com.spring.model.User;
import com.spring.services.UserAccountService;
import com.spring.services.UserSignInService;
import com.spring.services.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ClientRESTController {

    @Autowired
    private UserAccountService userAccountService; //data manipulation in the database
    @Autowired
    private UserSignUpService userSignUpService;
    @Autowired
    private UserSignInService userSignInService;



    //-------------------Create a User, SignUp--------------------------------------------------------

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("Creating User " + user.getUsername());

        String providedUsername = user.getUsername();

        userSignUpService.addUser(user); //added to the database

        User returnedUser = userSignInService.getUserByUsername(providedUsername);

        return new ResponseEntity<User>(returnedUser, HttpStatus.OK);

    }

    //------------------- Update a User, by the same user --------------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        userAccountService.updateUser(user);
        User currentUser = userAccountService.getUserById(user.getId());
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

}