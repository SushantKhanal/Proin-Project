package com.spring.controller;

import com.spring.model.FavProUsers;
import com.spring.model.NormalProfilePic;
import com.spring.model.NormalUser;
import com.spring.model.User;
import com.spring.requestDto.CustomEmailDTO;
import com.spring.requestDto.PicDataDto;
import com.spring.responseDto.CountriesList;
import com.spring.responseDto.FollowingDto;
import com.spring.responseDto.SearchParamsDto;
import com.spring.responseDto.ValueDto;
import com.spring.services.*;
import com.spring.utils.WebResourceConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

@RestController
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.NormalAccountCtrl.Normal_ACCOUNT_BASE)

public class NormalAccountCtrl {
    @Autowired
    private JavaMailSender mailSender;

    private final NormalSignUpService normalSignUpService;
    private final NormalSignInService normalSignInService;
    private final NormalAccountService normalAccountService;
    private final SearchProClientsService searchProClientsService;
    private final ViewProAccountService viewProAccountService;

    public NormalAccountCtrl(NormalSignUpService normalSignUpService, NormalSignInService normalSignInService,
                             NormalAccountService normalAccountService, SearchProClientsService searchProClientsService,
                             ViewProAccountService viewProAccountService) {
        this.normalSignUpService = normalSignUpService;
        this.normalSignInService = normalSignInService;
        this.normalAccountService = normalAccountService;
        this.searchProClientsService = searchProClientsService;
        this.viewProAccountService = viewProAccountService;
    }


    //EDIT NORMAL CLIENT PROFILE
    @PostMapping(WebResourceConstant.NormalAccountCtrl.UPDATE_USER)
    public ResponseEntity<NormalUser> createUser(@RequestBody NormalUser normalUser) {
        normalSignUpService.addNormalUser(normalUser);
        NormalUser returnedProfile = normalSignInService.getNormalUserByUsername(normalUser.getUsername());
        return new ResponseEntity<>(returnedProfile, HttpStatus.OK);
    }

    //UPDATES PROFILE PICS OF NORMAL CLIENTS
    @PostMapping(WebResourceConstant.NormalAccountCtrl.UPDATE_PROFILE_PIC)
    public ResponseEntity<NormalProfilePic> updateProfilePic(@RequestBody PicDataDto picData)
            throws IOException {
        NormalProfilePic normalProfilePic1;
        NormalUser returnedUser = normalSignInService.getNormalUserByUsername(picData.getUsername());
        Long id = returnedUser.getId();

        byte[] decodedImg = Base64.getDecoder().decode(picData.getImage());
        //NormalProfilePic NormalProfilePic1 = new NormalProfilePic();

        String picPath = File.separator + "proinProjectNormalImages/" + picData.getUsername() + "." + picData.getFileType();

        FileOutputStream imageOutFile = new FileOutputStream(System.getProperty("catalina.home") + picPath);

        try {
            imageOutFile.write(decodedImg);
        } catch (Exception e) {
            System.out.println("error");
        }

        //IF ISEXISTS == TRUE
        NormalProfilePic normalProfilePic = normalAccountService.getUserPpByUsername(picData.getUsername());
        if (normalProfilePic != null) {
            Long trueId = normalProfilePic.getId();
            normalProfilePic1 = new NormalProfilePic(picData.getUsername(), picPath, returnedUser);
            normalProfilePic1.setId(trueId);
        } else {
            normalProfilePic1 = new NormalProfilePic(picData.getUsername(), picPath, returnedUser);
        }
        //

        normalAccountService.addProfilePic(normalProfilePic1);

        NormalProfilePic normalProfilePic2 = normalAccountService.getUserPpByUsername(picData.getUsername());

        return new ResponseEntity<NormalProfilePic>(normalProfilePic2, HttpStatus.ACCEPTED);
    }

    //FETCH PROFILE PICTURE OF NORMAL CLIENTS
    @PostMapping(WebResourceConstant.NormalAccountCtrl.FETCH_PROFILE_PIC)
    public ResponseEntity<NormalProfilePic> createUser(@RequestBody String username) {
        NormalProfilePic normalProfilePic2 = normalAccountService.getUserPpByUsername(username);
        return new ResponseEntity<>(normalProfilePic2, HttpStatus.OK);
    }

    //FETCH COUNTRIES LIST
    @GetMapping(WebResourceConstant.NormalAccountCtrl.GET_COUNTRIES)
    public ResponseEntity<CountriesList> getCountries() {
        CountriesList countriesList = new CountriesList();
        return new ResponseEntity<>(countriesList, HttpStatus.OK);
    }

    // ******************************************************* //

    @GetMapping(WebResourceConstant.NormalAccountCtrl.FETCH_VALUE)
    public ResponseEntity<ValueDto> fetchValue() {
        //Value value = new Value(0L, 0L);
        BigInteger v = normalAccountService.updateValue();
        ValueDto valueDto = new ValueDto(v);
        return new ResponseEntity<ValueDto>(valueDto, HttpStatus.OK);
    }

    // ******************************************************* //
    //SEARCH FOR PRO CLIENTS BASED ON COUNTRY AND OTHER PARAMS
    @PostMapping(WebResourceConstant.NormalAccountCtrl.SEARCH_PRO_USERS)
    public ResponseEntity<List<String>> searchProUsers(@RequestBody SearchParamsDto searchParams) {
        String undefined = "undefined";

        if (searchParams.getCountry().equals(undefined)) {
            List<String> results = searchProClientsService.getResults(searchParams.getSearchThis());
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        List<String> results = searchProClientsService.findResults(searchParams.getCountry(),
                searchParams.getSearchThis());

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    //FETCH PRO ACCOUNT BY USERNAME
    @PostMapping(WebResourceConstant.NormalAccountCtrl.FETCH_PRO_USER)
    public ResponseEntity<User> getProUserProfile(@RequestBody String username) {
        User proUser = searchProClientsService.getProUserProfile(username);
        return new ResponseEntity<User>(proUser, HttpStatus.OK);
    }

    //FETCH FAV USERNAMES
    @PostMapping(WebResourceConstant.NormalAccountCtrl.LOAD_FAV_USERNAMES)
    public ResponseEntity<List<String>> loadFavUsernames(@RequestBody String loggedInUsername) {
        List<FavProUsers> favUsers = viewProAccountService.getResults(loggedInUsername);
        List<String> favUsernames = new ArrayList<String>();
        for (FavProUsers element : favUsers) {
            favUsernames.add(element.getFavProUsername());
        }
        return new ResponseEntity<>(favUsernames, HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.NormalAccountCtrl.FETCH_FOLLOWINGS)
    public ResponseEntity<List<FollowingDto>> fetchFollowings(@RequestBody String username) {
        return new ResponseEntity<>(normalAccountService.getFollowingsData(username), HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.NormalAccountCtrl.SEND_CUSTOM_EMAIL)
    public ResponseEntity<Void> sendCustomEmail(@RequestBody CustomEmailDTO customEmailDTO) {
        //send Email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customEmailDTO.getEmailId());
        message.setSubject(customEmailDTO.getSubject());
        message.setText(customEmailDTO.getBody());
        message.setFrom("ProinProject@gmail.com");
        try{
            mailSender.send(message);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
