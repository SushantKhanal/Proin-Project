package com.spring.controller;

import com.spring.model.NormalUser;
import com.spring.requestDto.SignInInfoDto;
import com.spring.services.NormalSignInService;
import com.spring.utils.WebResourceConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

@RestController
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.SignInCtrl.SIGN_IN_BASE)
public class NormalSignInCtrl {

    private final NormalSignInService normalSignInService;

    public NormalSignInCtrl(NormalSignInService normalSignInService) {
        this.normalSignInService = normalSignInService;
    }

    @PostMapping(WebResourceConstant.SignInCtrl.FETCH_NORMAL_USER)
    public ResponseEntity<NormalUser> createUser(@RequestBody SignInInfoDto signInInfoDto){
        //fetch normal client profile by username
        NormalUser returnedProfile = normalSignInService.getNormalUserByUsername(signInInfoDto.getUsername());
        if(signInInfoDto.getPassword().equals(returnedProfile.getPassword())) {
            return new ResponseEntity<>(returnedProfile, HttpStatus.OK);
        }
        //check if the password is same, return the profile
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
