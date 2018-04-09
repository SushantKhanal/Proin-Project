package com.spring.controller;

import com.spring.responseDto.NormalInfo;
import com.spring.services.FollowersService;
import com.spring.utils.WebResourceConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

@RestController
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.NormalFollowersCtrl.NORMAL_FOLLOWERS_BASE)
public class FollowersController {
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
}
