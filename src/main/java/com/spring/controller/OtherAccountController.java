package com.spring.controller;

import com.spring.model.User;
import com.spring.services.SearchResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OtherAccountController {

    @Autowired
    private SearchResultsService searchResultsService;

    //-------------------Retrieve loggedIn User and favourite User------------------//

    @PostMapping("/searchResults/otherAccount/")
    public ResponseEntity<String[]> addFavUsers(@RequestBody User loggedInUser,@RequestBody User favUser) {

        System.out.println("loggedInUser User " + loggedInUser.getUsername());
        System.out.println("Favourite User " + favUser.getUsername());

        return new ResponseEntity<String[]>(HttpStatus.BAD_REQUEST);
    }


}
