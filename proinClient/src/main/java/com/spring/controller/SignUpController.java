package com.spring.controller;

import com.spring.model.UserSignUpRequest;
import com.spring.model.UserSignUpRequestStatus;
import com.spring.responseDto.SendString;
import com.spring.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;


@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private JavaMailSender mailSender;


    //SignUpController
    //-------------------Create a User, SignUp--------------------------------------------------------

    @PostMapping("/users")
    public ResponseEntity<SendString> createUser(@RequestBody UserSignUpRequest userSignUpRequest) {

        String providedUsername = userSignUpRequest.getUsername();

        signUpService.addUserSignUpRequest(userSignUpRequest);

        //add status 1 to user sign up requests
        UserSignUpRequest returnedUSR = signUpService.getUserSignUpRequestByUsername(providedUsername);

        UserSignUpRequestStatus uSRS = new UserSignUpRequestStatus(1, returnedUSR.getUsername(), returnedUSR);
        signUpService.addUserSignUpRequestStatus(uSRS);

        //send Email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userSignUpRequest.getEmail());
        message.setSubject("About Proin app Sign Up");
        message.setText("Your sign up request was received, and is under review. We will follow up soon.");
        message.setFrom("ProinProject@gmail.com");
        try{
            mailSender.send(message);
        } catch(Exception e) {
            System.out.println(e);
        }
        //return username in object form
        SendString sendString = new SendString(providedUsername);

        return new ResponseEntity<>(sendString, HttpStatus.OK);
    }


}