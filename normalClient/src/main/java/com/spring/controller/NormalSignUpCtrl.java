package com.spring.controller;

import com.spring.model.NormalUser;
import com.spring.requestDto.NormalUserDto;
import com.spring.services.SignUpService;
import com.spring.utils.WebResourceConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.SignUpCtrl.SIGN_UP_BASE)
public class NormalSignUpCtrl {

    private final SignUpService signUpService;

    public NormalSignUpCtrl(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping(WebResourceConstant.SignUpCtrl.CREATE_USER)
    public ResponseEntity<Void> createUser(@RequestBody NormalUser normalUser){
        signUpService.addNormalUser(normalUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
