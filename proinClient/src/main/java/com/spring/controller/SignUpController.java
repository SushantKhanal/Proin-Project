package com.spring.controller;

import com.spring.model.UserSignUpRequest;
import com.spring.responseDto.SendString;
import com.spring.services.SignInService;
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
    private SignInService signInService;
    @Autowired
    private JavaMailSender mailSender;


    //SignUpController
    //-------------------Create a User, SignUp--------------------------------------------------------

    @PostMapping("/users")
    public ResponseEntity<SendString> createUser(@RequestBody UserSignUpRequest userSignUpRequest) {

        String providedUsername = userSignUpRequest.getUsername();

        //DON'T ADD TO USERS TABLE ON SIGN UP, ADD THEM TO USER SIGN UP REQUEST TABLE, AND SEND THEM MAILS, WHICH GETS ADDED
        //TO USERS TABLE ONLY ON ADMINS APPROVAL

        signUpService.addUserSignUpRequest(userSignUpRequest);

        String getMailTo = userSignUpRequest.getEmail();
        String emailSubject = "About Proin app Sign Up";
        String emailBody = "Your sign up request was received, and is under review. We will follow up soon.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(getMailTo);
        message.setSubject(emailSubject);
        message.setText(emailBody);
        message.setFrom("ProinProject@gmail.com");
        try{
            mailSender.send(message);
        } catch(Exception e) {
            System.out.println(e);
        }

//        signUpService.addUser(user); //added to the database
//
//        User returnedUser = signInService.getUserByUsername(providedUsername);
//
//        UserStatus userStatus = new UserStatus(1, providedUsername, returnedUser);
//
//        signUpService.addUserStatus(userStatus);

        SendString sendString = new SendString(providedUsername);

        return new ResponseEntity<>(sendString, HttpStatus.OK);

    }


}