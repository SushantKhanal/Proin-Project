package com.spring.controller;

import com.spring.model.*;
import com.spring.requestDto.CheckIfFollowedDto;
import com.spring.requestDto.FavDto;
import com.spring.requestDto.LoggedMessageDto;
import com.spring.requestDto.ReviewDto;
import com.spring.responseDto.ProUserDocInfo;
import com.spring.responseDto.SendStringDto;
import com.spring.services.NormalSignInService;
import com.spring.services.ViewProAccountService;
import com.spring.utils.WebResourceConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.ViewProAccountCtrl.VIEW_PRO_ACCOUNT_BASE)
public class ViewProAccountCtrl {

    private final ViewProAccountService viewProAccountService;

    private final NormalSignInService normalSignInService;

    public ViewProAccountCtrl(ViewProAccountService viewProAccountService, NormalSignInService normalSignInService) {
        this.viewProAccountService = viewProAccountService;
        this.normalSignInService = normalSignInService;
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.CHECK_IF_FOLLOWED)
    public ResponseEntity<SendStringDto>checkIfFollowed(@RequestBody CheckIfFollowedDto checkIfFollowedDto) {
        SendStringDto sendStringDto = new SendStringDto(viewProAccountService.checkIfFollowed(checkIfFollowedDto));
        return new ResponseEntity<SendStringDto>(sendStringDto, HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.UNFOLLOW)
    public ResponseEntity<Void>unfollow(@RequestBody CheckIfFollowedDto checkIfFollowedDto) {
        try {
            viewProAccountService.unfollow(checkIfFollowedDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.SEND_FOLLOW_REQUEST)
    public ResponseEntity<Void>sendFollowRequest(@RequestBody LoggedMessageDto followRequest) {
        //check for past follow requests
        NormalFollowRequest normalFollowRequest;
        try {
            Long trueId = viewProAccountService.checkPastRequests(followRequest);
            normalFollowRequest = new NormalFollowRequest(trueId, followRequest.getFromNormalUsername(),
                    followRequest.getToProUsername(), followRequest.getMessage(), 0L);
        }
        catch (Exception e) {
            normalFollowRequest = new NormalFollowRequest(followRequest.getFromNormalUsername(),
                    followRequest.getToProUsername(), followRequest.getMessage(), 0L);
        }

        viewProAccountService.registerFollowRequest(normalFollowRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.FETCH_PRO_PROFILE_PIC)
    public ResponseEntity<UserProfilePic> getProProfilePic(@RequestBody String  username){

        return new ResponseEntity<>(viewProAccountService.getProProfilePic(username), HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.FETCH_TAGS)
    public ResponseEntity<UserTags> receiveTags(@RequestBody String  username){
        return new ResponseEntity<>(viewProAccountService.receiveTags(username),HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.FETCH_ALL_EXPERIENCE)
    public ResponseEntity<List<UserExperience>> getAllExperience(@RequestBody String  username){
        return new ResponseEntity<>(viewProAccountService.getAllExperience(username), HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.FETCH_ALL_ACADEMICS)
    public ResponseEntity<List<UserAcademics>> getAllAcademics(@RequestBody String  username){
        return new ResponseEntity<>(viewProAccountService.getAllAcademics(username), HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.FETCH_REVIEWS)
    public ResponseEntity<List<NormalUserReviews>> getReviews(@RequestBody String  username){
        return new ResponseEntity<>(viewProAccountService.getReviews(username), HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.NORMAL_SENDS_PRO_FAV)
    public ResponseEntity<Void> sendFav(@RequestBody FavDto favDto) {
        viewProAccountService.addFav(favDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.NORMAL_DELETES_PRO_Fav)
    public ResponseEntity<Void> deleteFav(@RequestBody FavDto favDto) {

        String loggedInNormalUsername = favDto.getLoggedInNormalUser();
        String favProUsername = favDto.getFavProUser();

        List<FavProUsers> favUsers = viewProAccountService.getResults(loggedInNormalUsername);

        for (FavProUsers element : favUsers) {
            if (element.getFavProUsername().equals(favProUsername)) {
                Long favId = element.getId();
                viewProAccountService.deleteFav(favId);
            }
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.NORMAL_CHECKS_PRO_FAV)
    public ResponseEntity<Void> checkIfFav(@RequestBody FavDto favDto) {

        String loggedInUsername = favDto.getLoggedInNormalUser();
        String favUsername = favDto.getFavProUser();

        List<FavProUsers> favUsers = viewProAccountService.getResults(loggedInUsername);

        for (FavProUsers element : favUsers) {
            if (element.getFavProUsername().equals(favUsername)) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.POST_REVIEW)
    public ResponseEntity<Void> postReview(@RequestBody ReviewDto reviewDTO1) {
        //check if exists
        //add or update the review
        //create a new Review-ClientType table, where "Normal" is added as client type
        String loggedInUsername = reviewDTO1.getLoggedInUsername();
        String otherUsername = reviewDTO1.getOtherUsername();
        String review = reviewDTO1.getReview();
        Integer rating = reviewDTO1.getRating();

        NormalUser loggedInNormalUser = normalSignInService.getNormalUserByUsername(loggedInUsername);

        List<NormalUserReviews> normalUserReviews = viewProAccountService.getNormalReviews(loggedInUsername);
        //checking if the user has already reviewed this account, if true, update, instead of create
        for (NormalUserReviews element : normalUserReviews) {
            String usernameOther = element.getOtherUsername();
            if (usernameOther.equals(otherUsername)) {
                Long id = element.getId();
                NormalUserReviews normalUserReviews1 = new NormalUserReviews(id, loggedInUsername, otherUsername, review, rating, loggedInNormalUser);
                try {
                    viewProAccountService.addNormalReview(normalUserReviews1);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                }
            }
        }

        NormalUserReviews normalUserReviews2 = new NormalUserReviews(loggedInUsername, otherUsername, review, rating, loggedInNormalUser);

        try {
            viewProAccountService.addNormalReview(normalUserReviews2);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(WebResourceConstant.ViewProAccountCtrl.CHECK_FOR_UPLOADED_DOCS)
    public ResponseEntity<List<ProUserDocInfo>> checkForUploadedDocs(@RequestBody String username) {
        return new ResponseEntity<>(viewProAccountService.checkForUploadedDocs(username), HttpStatus.OK);
    }
}
