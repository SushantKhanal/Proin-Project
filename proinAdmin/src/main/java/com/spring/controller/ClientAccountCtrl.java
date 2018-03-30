package com.spring.controller;

        import com.spring.model.*;
        import com.spring.responseDTO.ReviewInfo;
        import com.spring.responseDTO.TagsInfo;
        import com.spring.services.AdminAccountService;
        import com.spring.services.ClientAccountService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.ArrayList;
        import java.util.List;

@RestController
public class ClientAccountCtrl {

    @Autowired
    ClientAccountService clientAccountService;

    @Autowired
    AdminAccountService adminAccountService;


    //SENDS ADMIN SELECTED USER ACADEMICS
    @PostMapping("/client/getAllAcademics/")
    public ResponseEntity<List<UserAcademics>> getAllAcademics(@RequestBody String loggedInUsername) {

        List<UserAcademics> userAllAcads = clientAccountService.getUserAcademicsByUsername(loggedInUsername);

        return new ResponseEntity<>(userAllAcads, HttpStatus.OK);
    }

    //SENDS ADMIN SELECTED USER EXPERIENCE
    @PostMapping("/client/getAllExperience/")
    public ResponseEntity<List<UserExperience>> getAllExperience(@RequestBody String username) {

        List<UserExperience> userAllExp = clientAccountService.getUserExperienceByUsername(username);

        return new ResponseEntity<>(userAllExp, HttpStatus.OK);
    }

    //SENDS ADMIN SELECTED USER PROFILE PIC
    @PostMapping("/client/getProfilePic/")
    public ResponseEntity<UserProfilePic> getProfilePic(@RequestBody String username) {

        UserProfilePic returnedProfilePic = clientAccountService.getUserPpByUsername(username);

        if (returnedProfilePic != null) {
            return new ResponseEntity<UserProfilePic>(returnedProfilePic, HttpStatus.OK);
        }

        return new ResponseEntity<UserProfilePic>(HttpStatus.BAD_REQUEST);
    }

    //SENDS ADMIN SELECTED USER REVIEWS
    @PostMapping("/client/getReviews/")
    public ResponseEntity<List<ReviewInfo>> getReviews(@RequestBody String otherUsername) {

        List<UserReviews> userReviews2 = clientAccountService.getAllReviews(otherUsername);

        List<ReviewInfo> reviewInfoList= new ArrayList<ReviewInfo>();

        for (UserReviews element : userReviews2) {
            String loggedInUsername = element.getLoggedInUsername();
            String review = element.getReview();
            Integer rating = element.getRating();
            ReviewInfo reviewInfo1 = new ReviewInfo(loggedInUsername, otherUsername, review, rating);
            reviewInfoList.add(reviewInfo1);
        }

        return new ResponseEntity<List<ReviewInfo>>(reviewInfoList, HttpStatus.OK);
    }


    //SENDS ADMIN SELECTED USER TAGS
    @PostMapping("/client/receiveTags/")
    public ResponseEntity<TagsInfo> receiveTags(@RequestBody String loggedInUsername) {

        UserTags userTags = clientAccountService.getUserTagsByUsername(loggedInUsername);

        String tags = userTags.getTags();

        TagsInfo tagsInfo = new TagsInfo(tags);

        return new ResponseEntity<>(tagsInfo, HttpStatus.OK);
    }

    //SENDS ADMIN THE SELECTED USER'S FAVOURITE USERS
    @PostMapping("/client/receiveFavs/")
    public ResponseEntity<List<String>> receiveFavs(@RequestBody String loggedInUsername) {

        List<FavUsers> favUsers = clientAccountService.getResults(loggedInUsername);
        List<String> listOfFavUsers = new ArrayList<String>();
        for (FavUsers element : favUsers) {
            String favUsername = element.getFavUsername();
            listOfFavUsers.add(favUsername);
        }

        return new ResponseEntity<>(listOfFavUsers, HttpStatus.OK);
    }

    //DELETES THE CLIENT ACCOUNT ADMIN WANTS
    @PostMapping("/client/deleteThisAccount/")
    public ResponseEntity<Void> deleteThisAccount(@RequestBody String username) {

        //add status 0 for deleted accounts and 1 for those not deleted
        User user = adminAccountService.getUserByUsername(username);
        UserStatus ust = adminAccountService.getUserStatusByUsername(username);
        Long ustId = ust.getId();
        UserStatus userStatus = new UserStatus(ustId,0, username, user);
        clientAccountService.addUserStatus(userStatus);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
