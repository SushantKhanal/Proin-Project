package com.spring.controller;

import com.spring.dto.FavUserDTO;
import com.spring.dto.ReviewDTO;
import com.spring.model.FavUsers;
import com.spring.model.User;
import com.spring.services.OtherAccountService;
import com.spring.services.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class OtherAccountController {

    @Autowired
    private OtherAccountService otherAccountService;

    @Autowired
    private SignInService signInService;

    //-------------------CREATE FAVOURITE USER RECORD IN DATABASE------------------//

    @PostMapping("/searchResults/otherAccount/")
    public ResponseEntity<Void> addFavUsers(@RequestBody FavUserDTO favUserDTO) {

        String loggedInUsername = favUserDTO.getLoggedInUser();
        String favUsername = favUserDTO.getFavUser();
        User loggedInUser = signInService.getUserByUsername(loggedInUsername);
        FavUsers favUsers1 = new FavUsers(loggedInUsername, favUsername, loggedInUser);

        try {
            otherAccountService.addFavouriteUser(favUsers1);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

    }

    //CHECKS IF THE OTHER ACCOUNT IS ALREADY A FAV OF THE LOGGED IN ACCOUNT
    @PostMapping("/searchResults/otherAccount/checkIfFav/")
    public ResponseEntity<Void> checkIfFav(@RequestBody FavUserDTO favUserDTO1) {

        String loggedInUsername = favUserDTO1.getLoggedInUser();
        String favUsername = favUserDTO1.getFavUser();

        List<FavUsers> favUsers = otherAccountService.getResults(loggedInUsername);

        for (FavUsers element : favUsers) {
            if (element.getFavUsername().equals(favUsername)) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

    }

    //DELETES THE FAV PERSON FROM THE LOGGED IN ACCOUNT
    @PostMapping("/searchResults/otherAccount/deleteFav/")
    public ResponseEntity<Void> deleteFav(@RequestBody FavUserDTO favUserDTO2) {

        String loggedInUsername = favUserDTO2.getLoggedInUser();
        String favUsername = favUserDTO2.getFavUser();


        List<FavUsers> favUsers = otherAccountService.getResults(loggedInUsername);

        for (FavUsers element : favUsers) {
            if (element.getFavUsername().equals(favUsername)) {
                Long favId = element.getId();
                otherAccountService.deleteFav(favId);
            }
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //ADDS REVIEW TO DATABASE
    @PostMapping("/searchResults/otherAccount/review/")
    public ResponseEntity<Void> postReview(@RequestBody ReviewDTO reviewDTO1) {

        String loggedInUsername = reviewDTO1.getLoggedInUsername();
        String otherUsername = reviewDTO1.getOtherUsername();
        String review = reviewDTO1.getReview();

        return new ResponseEntity<Void>(HttpStatus.OK);

    }

}
