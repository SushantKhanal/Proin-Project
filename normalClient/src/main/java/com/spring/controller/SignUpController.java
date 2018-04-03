package com.spring.controller;

import com.spring.requestDto.NormalUserDto;
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
public class SignUpController {
    @PostMapping(WebResourceConstant.SignUpCtrl.CREATE_USER)
    public ResponseEntity<Void> createUser(@RequestBody NormalUserDto user){

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
