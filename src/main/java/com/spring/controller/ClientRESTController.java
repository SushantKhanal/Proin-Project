package com.spring.controller;

import com.spring.model.User;
import com.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClientRESTController {

    @Autowired
    private UserService userService;  //Service which will do all data retrieval/manipulation work


    //-------------------Retrieve All Users--------------------------------------------------------

    @GetMapping("/users/")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    //-------------------Create a User--------------------------------------------------------


    @PostMapping("/users/")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        System.out.println("Creating User " + user.getUsername());

        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        User currentUser = userService.findById(id);

        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.setId(user.getId());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setDob(user.getDob());
        currentUser.setBio(user.getBio());
        currentUser.setNation(user.getNation());
        currentUser.setClientType(user.getClientType());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        currentUser.setAgenda(user.getAgenda());
        currentUser.setJoinDate(user.getJoinDate());
        currentUser.setAcademics(user.getAcademics());
        currentUser.setExperience(user.getExperience());
        currentUser.setMarketDomain(user.getMarketDomain());

        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }


}