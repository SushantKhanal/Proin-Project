package com.spring.controller;

import com.spring.model.*;
import com.spring.requestDto.*;
import com.spring.responseDto.TagsInfo;
import com.spring.responseDto.UserDocInfo;
import com.spring.services.AccountService;
import com.spring.services.OtherAccountService;
import com.spring.services.SignInService;
import com.spring.utils.WebResourceConstant;
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
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.AccountCtrl.USER_ACCOUNT_BASE)
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private SignInService signInService;

    @Autowired
    private OtherAccountService otherAccountService;
    //------------------- Update a User, by the same user --------------------------------------------------------

    @PutMapping(WebResourceConstant.AccountCtrl.UPDATE_USER)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        accountService.updateUser(user);
        User currentUser = accountService.getUserById(user.getId());
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

//PRO USER UPLOADS DOCUMENTS, AND IT'S SAVED IN 'proinProjectdoc' FOLDER IN CATALINA HOME
    @PostMapping(WebResourceConstant.AccountCtrl.POST_DOC)
    public ResponseEntity<Void> postDoc(@RequestBody DocInfo docInfo)
            throws IOException {

        User returnedUser = signInService.getUserByUsername(docInfo.getUsername());
        Long id = returnedUser.getId();

        byte[] decodedDoc = Base64.getDecoder().decode(docInfo.getDoc());
        //UserProfilePic userProfilePic1 = new UserProfilePic();

        String docPath = File.separator+"proinProjectdoc/"+docInfo.getUsername()+"-"+
                docInfo.getFileName()+"."+docInfo.getFileType();

        FileOutputStream docOutFile = new FileOutputStream(System.getProperty("catalina.home")+ docPath);

        try {
            docOutFile.write(decodedDoc);
        }
        catch(Exception e){
            System.out.println("error");
        }
        UserDocuments userDocument = new UserDocuments(docInfo.getUsername(), docPath, returnedUser);
        //NOW SAVE THE docPath IN A TABLE
        accountService.saveUserDoc(userDocument);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    // NOT DONE YET
    @PostMapping(WebResourceConstant.AccountCtrl.CHECK_FOR_UPLOADED_DOCS)
    public ResponseEntity<List<UserDocInfo>> checkForUploadedDocs(@RequestBody String username) {
        return new ResponseEntity<>(accountService.checkForUploadedDocs(username), HttpStatus.OK);
    }

    //----------------WHEN USER POSTS PROFILE PICTURE-----------------------------------------------------//

    @PostMapping(WebResourceConstant.AccountCtrl.POST_PROFILE_PIC)
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


        //IF ISEXISTS == TRUE
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

    //RETURNS PROFILE PIC BASED ON USERNAME
    @PostMapping(WebResourceConstant.AccountCtrl.GET_PROFILE_PIC)
    public ResponseEntity<UserProfilePic> getProfilePic(@RequestBody String username) {

        UserProfilePic returnedProfilePic = accountService.getUserPpByUsername(username);

        if (returnedProfilePic != null) {
            return new ResponseEntity<UserProfilePic>(returnedProfilePic, HttpStatus.OK);
        }

        return new ResponseEntity<UserProfilePic>(HttpStatus.BAD_REQUEST);
    }

    //FETCHES THE LIST OF FAVOURITE ACCOUNTS
    @PostMapping(WebResourceConstant.AccountCtrl.GET_FAV_USERS)
    public ResponseEntity<List<String>> getFavUsers(@RequestBody String loggedInUsername) {

        List<FavUsers> favUsers = otherAccountService.getResults(loggedInUsername);
        List<String> listOfFavUsers = new ArrayList<String>();
        for (FavUsers element : favUsers) {
            String favUsername = element.getFavUsername();
            listOfFavUsers.add(favUsername);
        }

        return new ResponseEntity<>(listOfFavUsers, HttpStatus.OK);
    }

    //SENDS OTHER USER PROFILE BASED ON USERNAME
    @PostMapping(WebResourceConstant.AccountCtrl.GET_USER_PROFILE)
    public ResponseEntity<User> getUserProfile(@RequestBody String otherAccountUsername) {

        User otherUser = signInService.getUserByUsername(otherAccountUsername);

        return new ResponseEntity<>(otherUser, HttpStatus.OK);
    }

    //SAVES TAGS
    @PostMapping(WebResourceConstant.AccountCtrl.SEND_TAGS)
    public ResponseEntity<Void> savesTags(@RequestBody UserTagsDTO usertagsdto) {
        String loggedInUsername = usertagsdto.getUsername();
        String tags = usertagsdto.getTags();
        User returnedUser = signInService.getUserByUsername(loggedInUsername);

        UserTags userTags = accountService.getUserTagsByUsername(loggedInUsername);

        //CHECK IF USERTAGS IS NULL

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

    //SENDS TAGS BASED ON USERNAME
    @PostMapping(WebResourceConstant.AccountCtrl.RECIEVE_TAGS)
    public ResponseEntity<TagsInfo> receiveTags(@RequestBody String loggedInUsername) {

        UserTags userTags = accountService.getUserTagsByUsername(loggedInUsername);

        String tags = userTags.getTags();

        TagsInfo tagsInfo = new TagsInfo(tags);

        return new ResponseEntity<>(tagsInfo, HttpStatus.OK);
    }

    //WHEN USER SENDS EXPERIENCE
    @PostMapping(WebResourceConstant.AccountCtrl.USER_EXPERIENCE)
    public ResponseEntity<Void> receiveExperience(@RequestBody UserExperienceDTO exp) {

        String loggedInUsername = exp.getUsername();

        User returnedUser = signInService.getUserByUsername(loggedInUsername);

        Long experienceId = exp.getId();

        //UserExperience userExp = accountService.getUserExperienceByUsername(loggedInUsername);

        UserExperience exp1;

        if (experienceId != null) {
            exp1 = new UserExperience(experienceId, exp.getUsername(), exp.getTitle(),
                    exp.getCompany(), exp.getLocation(), exp.getStartDate(), exp.getEndDate(),
                    exp.getDescription(), returnedUser);
        } else {
            exp1 = new UserExperience(exp.getUsername(), exp.getTitle(),
                    exp.getCompany(), exp.getLocation(), exp.getStartDate(), exp.getEndDate(),
                    exp.getDescription(), returnedUser);
        }

        accountService.addUserExperience(exp1);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    //SEND USER ALL HIS EXPERIENCE
    @PostMapping(WebResourceConstant.AccountCtrl.GET_ALL_EXPERIENCE)
    public ResponseEntity<List<UserExperience>> receiveExperience(@RequestBody String username) {

        List<UserExperience> userAllExp = accountService.getUserExperienceByUsername(username);
        return new ResponseEntity<>(userAllExp, HttpStatus.OK);
    }

    //WHEN USER SAVES ACADEMICS
    @PostMapping(WebResourceConstant.AccountCtrl.USER_ACADEMICS)
    public ResponseEntity<Void> receiveAcademics(@RequestBody UserAcademicsDTO acd) {

        String loggedInUsername = acd.getUsername();

        Long academicsId = acd.getId();

        User returnedUser = signInService.getUserByUsername(loggedInUsername);

        UserAcademics academics1;
        //EDIT FEATURE
        if (academicsId != null) {
            academics1 = new UserAcademics(academicsId, acd.getUsername(), acd.getDegree(),
                    acd.getSchool(), acd.getLocation(), acd.getStartDate(), acd.getEndDate(),
                    acd.getDescription(), returnedUser);
        } else {
            academics1 = new UserAcademics(acd.getUsername(), acd.getDegree(),
                    acd.getSchool(), acd.getLocation(), acd.getStartDate(), acd.getEndDate(),
                    acd.getDescription(), returnedUser);
        }

        accountService.addUserAcademics(academics1);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    //SEND USER ALL HIS ACADEMICS
    @PostMapping(WebResourceConstant.AccountCtrl.GET_ALL_ACADEMICS)
    public ResponseEntity<List<UserAcademics>> getAcademics(@RequestBody String loggedInUsername) {

        List<UserAcademics> userAllAcads = accountService.getUserAcademicsByUsername(loggedInUsername);

        return new ResponseEntity<>(userAllAcads, HttpStatus.OK);
    }

    //SEND USER THE ACADEMICS HE WANTS TO EDIT
    @PostMapping(WebResourceConstant.AccountCtrl.GET_ACADEMIC_FROM_ID)
    public ResponseEntity<UserAcademics> getAcademicFromId(@RequestBody Long id) {

        UserAcademics userAcd = accountService.getUserAcademicsFromId(id);

        return new ResponseEntity<>(userAcd, HttpStatus.OK);
    }

    //DELETE THE ACADEMICS THE USER WANTS TO DELETE
    @PostMapping(WebResourceConstant.AccountCtrl.DELETE_THIS_ACADEMICS)
    public ResponseEntity<Void> deleteThisAcademics(@RequestBody Long id) {

        accountService.deleteThisAcademics(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //SEND USER THE EXPERIENCE HE WANTS TO EDIT
    @PostMapping(WebResourceConstant.AccountCtrl.GET_EXPERIENCE_FROM_ID)
    public ResponseEntity<UserExperience> getExperienceFromId(@RequestBody Long id) {

        UserExperience userExperience = accountService.getExperienceFromId(id);

        return new ResponseEntity<>(userExperience, HttpStatus.OK);
    }

    //DELETE THE ACADEMICS THE USER WANTS TO DELETE
    @PostMapping(WebResourceConstant.AccountCtrl.DELETE_THIS_EXPERIENCE)
    public ResponseEntity<Void> deleteThisExperience(@RequestBody Long id) {

        accountService.deleteThisExperience(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //CHECKS AND RETURNS FOLLOW REQUESTS
    @PostMapping(WebResourceConstant.AccountCtrl.CHECK_FOLLOW_REQUESTS)
    public ResponseEntity<List<NormalFollowRequest>> checkFollowRequests(@RequestBody String username) {
        List<NormalFollowRequest> followRequests = accountService.checkFollowRequests(username);
        return new ResponseEntity<List<NormalFollowRequest>>(followRequests, HttpStatus.OK);
    }

    //APPROVES FOLLOW REQUESTS
    @PostMapping(WebResourceConstant.AccountCtrl.ACCEPT_FOLLOW_REQUESTS)
    public ResponseEntity<Void> acceptFollowRequest(@RequestBody Long id) {
        accountService.approveFollowRequest(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //IGNORES FOLLOW REQUEST
    @PostMapping(WebResourceConstant.AccountCtrl.IGNORE_FOLLOW_REQUESTS)
    public ResponseEntity<Void> ignoreFollowRequest(@RequestBody Long id) {
        accountService.ignoreFollowRequest(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //FETCHES IGNORED REQUESTS
    @PostMapping(WebResourceConstant.AccountCtrl.GET_IGNORED_REQUESTS)
    public ResponseEntity<List<NormalFollowRequest>> getIgnoredRequests() {
        List<NormalFollowRequest> list =  accountService.getIgnoredRequests();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.AccountCtrl.DELETE_DOCUMENT)
    public ResponseEntity<Void> deleteDocument(@RequestBody Long id) {
        accountService.deleteDocument(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
