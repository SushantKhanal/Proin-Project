package com.spring.controller;

import com.spring.model.FavUsers;
import com.spring.model.User;
import com.spring.model.UserProfilePic;
import com.spring.model.UserTags;
import com.spring.requestDto.UserExperienceDTO;
import com.spring.requestDto.UserTagsDTO;
import com.spring.responseDto.TagsInfo;
import com.spring.services.AccountService;
import com.spring.services.OtherAccountService;
import com.spring.services.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private SignInService signInService;

    @Autowired
    private OtherAccountService otherAccountService;
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

    //returns profile pic based on username
    @PostMapping("/user/getProfilePic/")
    public ResponseEntity<UserProfilePic> getProfilePic(@RequestBody String username) {

        UserProfilePic returnedProfilePic = accountService.getUserPpByUsername(username);

        if (returnedProfilePic != null) {
            return new ResponseEntity<UserProfilePic>(returnedProfilePic, HttpStatus.OK);
        }

        return new ResponseEntity<UserProfilePic>(HttpStatus.BAD_REQUEST);
    }

    //FETCHES THE LIST OF FAVOURITE ACCOUNTS
    @PostMapping("/user/getFavUsers/")
    public ResponseEntity<List<String>> getFavUsers(@RequestBody String loggedInUsername) {

        List<FavUsers> favUsers = otherAccountService.getResults(loggedInUsername);
        List<String> listOfFavUsers = new ArrayList<String>();
        for (FavUsers element : favUsers) {
            String favUsername = element.getFavUsername();
            listOfFavUsers.add(favUsername);
        }

        return new ResponseEntity<>(listOfFavUsers, HttpStatus.OK);
    }

    //sends other user profile based on username
    @PostMapping("/user/getUserProfile/")
    public ResponseEntity<User> getUserProfile(@RequestBody String otherAccountUsername) {

        User otherUser = signInService.getUserByUsername(otherAccountUsername);

        return new ResponseEntity<>(otherUser, HttpStatus.OK);
    }

    //saves tags
    @PostMapping("/user/sendTags/")
    public ResponseEntity<Void> savesTags(@RequestBody UserTagsDTO usertagsdto) {
        String loggedInUsername = usertagsdto.getUsername();
        String tags = usertagsdto.getTags();
        User returnedUser = signInService.getUserByUsername(loggedInUsername);

        UserTags userTags = accountService.getUserTagsByUsername(loggedInUsername);

        //check if userTags is null

        if (userTags != null) {
            Long trueId = userTags.getId();
            UserTags userTags2 = new UserTags(trueId, loggedInUsername, tags, returnedUser);
            accountService.addUserTags(userTags2);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        UserTags userTags1 = new UserTags(loggedInUsername, tags, returnedUser);

        accountService.addUserTags(userTags1);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    //sends tags based on username
    @PostMapping("/user/receiveTags/")
    public ResponseEntity<TagsInfo> receiveTags(@RequestBody String loggedInUsername) {

        UserTags userTags = accountService.getUserTagsByUsername(loggedInUsername);

        String tags = userTags.getTags();

        TagsInfo tagsInfo = new TagsInfo(tags);

        return new ResponseEntity<>(tagsInfo, HttpStatus.OK);
    }

    //WHEN USER SENDS EXPERIENCE
    @PostMapping("/user/userExperience/")
    public ResponseEntity<Void> receiveExperience(@RequestBody UserExperienceDTO exp) {

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
