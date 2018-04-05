package com.spring.controller;

import com.spring.model.*;
import com.spring.requestDto.FavDto;
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

    public ViewProAccountCtrl(ViewProAccountService viewProAccountService) {
        this.viewProAccountService = viewProAccountService;
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
    public ResponseEntity<List<UserReviews>> getReviews(@RequestBody String  username){
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
}
