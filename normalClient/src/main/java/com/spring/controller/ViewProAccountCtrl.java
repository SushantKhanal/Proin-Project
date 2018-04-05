package com.spring.controller;

import com.spring.model.*;
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

}
