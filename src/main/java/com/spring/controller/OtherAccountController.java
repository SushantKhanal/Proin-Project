package com.spring.controller;

import com.spring.dto.FavUserDTO;
import com.spring.model.FavUsers;
import com.spring.model.User;
import com.spring.services.OtherAccountService;
import com.spring.services.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OtherAccountController {

    @Autowired
    private OtherAccountService otherAccountService;

    @Autowired
    private SignInService signInService;

    //-------------------Retrieve loggedIn User and favourite User------------------//

    @PostMapping("/searchResults/otherAccount/")
    public ResponseEntity<Void> addFavUsers(@RequestBody FavUserDTO favUserDTO) {

        String loggedInUsername = favUserDTO.getLoggedInUser();
        String favUsername = favUserDTO.getFavUser();
        User loggedInUser = signInService.getUserByUsername(loggedInUsername);
        FavUsers favUsers1 = new FavUsers(loggedInUsername, favUsername, loggedInUser);

        try {
            otherAccountService.addFavouriteUser(favUsers1);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

    }

    //checks if the other username is already a fav of the loggedIn username
    @PostMapping("/searchResults/otherAccount/checkIfFav/")
    public ResponseEntity<Void> updateUser(@RequestBody FavUserDTO favUserDTO1) {

        String loggedInUsername = favUserDTO1.getLoggedInUser();
        String favUsername = favUserDTO1.getFavUser();

        System.out.println("loggedInUsername = " + loggedInUsername);
        System.out.println("favUsername = " + favUsername);

        List<FavUsers> favUserss= otherAccountService.getResults(loggedInUsername);

        for (FavUsers element: favUserss) {
            if(element.getFavUsername().equals(favUsername)) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

    }

}
