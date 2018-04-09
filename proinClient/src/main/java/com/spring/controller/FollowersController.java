package com.spring.controller;

import com.spring.requestDto.CustomEmailDTO;
import com.spring.responseDto.NormalInfo;
import com.spring.services.FollowersService;
import com.spring.utils.WebResourceConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.NormalFollowersCtrl.NORMAL_FOLLOWERS_BASE)
public class FollowersController {

    @Autowired
    private JavaMailSender mailSender;
    private final FollowersService followersService;
    public FollowersController(FollowersService followersService) {
        this.followersService = followersService;
    }

    @PostMapping(WebResourceConstant.NormalFollowersCtrl.FETCH_FOLLOWERS)
    public ResponseEntity<List<NormalInfo>> fetchFollowers(@RequestBody String username) {
        //find followers from normal follow request table(status==1)
        //find the email of those followers from normal user table
        List<NormalInfo> list = followersService.getNormalUserEmails(username);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.NormalFollowersCtrl.SEND_CUSTOM_EMAIL)
    public ResponseEntity<Void> sendEmail(@RequestBody CustomEmailDTO customEmailDTO) {
        //send Email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customEmailDTO.getEmailId());
        message.setSubject(customEmailDTO.getSubject());
        message.setText(customEmailDTO.getBody());
        message.setFrom("ProinProject@gmail.com");
        try {
            mailSender.send(message);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
