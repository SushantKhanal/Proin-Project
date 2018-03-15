package com.spring.controller;

import com.spring.model.User;
import com.spring.model.UserProfilePic;
import com.spring.services.AccountService;
import com.spring.services.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private SignInService signInService;
    //------------------- Update a User, by the same user --------------------------------------------------------

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        accountService.updateUser(user);
        User currentUser = accountService.getUserById(user.getId());
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    //----------------When user posts profile picture-----------------------------------------------------//

    @PostMapping("/user/profilePic/{username}/{fileType}")
    public ResponseEntity<UserProfilePic> postProfilePic(@PathVariable("username") String username,@PathVariable("fileType") String fileType, @RequestBody String image)
            throws IOException {
        UserProfilePic userProfilePic1;
        User returnedUser = signInService.getUserByUsername(username);
        Long id = returnedUser.getId();

        byte[] decodedImg = Base64.getDecoder().decode(image);
        //UserProfilePic userProfilePic1 = new UserProfilePic();

        String picPath = File.separator+"proinProjectImages/"+username+"."+fileType;

        FileOutputStream imageOutFile = new FileOutputStream(System.getProperty("catalina.home")+ picPath);

        try {
            imageOutFile.write(decodedImg);
        }
        catch(Exception e){
            System.out.println("error");
        }


        //if isExists == true
        UserProfilePic userProfilePic = accountService.getUserPpByUsername(username);
        if(userProfilePic != null) {
            Long trueId = userProfilePic.getId();
            userProfilePic1 = new UserProfilePic(username, picPath, returnedUser);
            userProfilePic1.setId(trueId);
        }else {
            userProfilePic1 = new UserProfilePic(username, picPath, returnedUser);
        }
        //
        accountService.addProfilePic(userProfilePic1);

        UserProfilePic userProfilePic2 = accountService.getUserPpByUsername(username);


        return new ResponseEntity<UserProfilePic>(userProfilePic2, HttpStatus.ACCEPTED);

    }

}
