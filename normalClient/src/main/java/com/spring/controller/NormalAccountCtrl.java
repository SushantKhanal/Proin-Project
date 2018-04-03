package com.spring.controller;

import com.spring.model.NormalUser;
import com.spring.requestDto.SignInInfoDto;
import com.spring.services.NormalSignInService;
import com.spring.services.NormalSignUpService;
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
        WebResourceConstant.NormalAccountCtrl.Normal_ACCOUNT_BASE)
public class NormalAccountCtrl {
    private final NormalSignUpService normalSignUpService;
    private final NormalSignInService normalSignInService;

    public NormalAccountCtrl(NormalSignUpService normalSignUpService, NormalSignInService normalSignInService) {
        this.normalSignUpService = normalSignUpService;
        this.normalSignInService = normalSignInService;
    }

    @PostMapping(WebResourceConstant.NormalAccountCtrl.UPDATE_USER)
    public ResponseEntity<NormalUser> createUser(@RequestBody NormalUser normalUser){
        normalSignUpService.addNormalUser(normalUser);
        NormalUser returnedProfile = normalSignInService.getNormalUserByUsername(normalUser.getUsername());
        return new ResponseEntity<>(returnedProfile, HttpStatus.OK);
    }
}
