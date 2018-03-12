package com.spring.controller;

import com.spring.model.User;
import com.spring.services.UserDatabaseService;
import com.spring.services.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class ClientRESTController {

    @Autowired
    private UserDatabaseService userDatabaseService; //data manipulation in the database
    @Autowired
    private UserSignUpService userSignUpService;


    //-------------------Retrieve All Users--------------------------------------------------------

    @GetMapping("/users")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userDatabaseService.listUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    //-------------------Create a User--------------------------------------------------------


    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        System.out.println("Creating User " + user.getUsername());

        userSignUpService.addUser(user); //added to the database

        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        userDatabaseService.updateUser(user);
        User currentUser = userDatabaseService.getUserById(user.getId());
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }


}