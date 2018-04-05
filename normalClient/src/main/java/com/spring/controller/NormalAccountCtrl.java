package com.spring.controller;

import com.spring.model.NormalProfilePic;
import com.spring.model.NormalUser;
import com.spring.requestDto.PicDataDto;
import com.spring.responseDto.CountriesList;
import com.spring.responseDto.SearchParamsDto;
import com.spring.responseDto.ValueDto;
import com.spring.services.NormalAccountService;
import com.spring.services.NormalSignInService;
import com.spring.services.NormalSignUpService;
import com.spring.utils.WebResourceConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Base64;

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
    private final NormalAccountService normalAccountService;

    public NormalAccountCtrl(NormalSignUpService normalSignUpService, NormalSignInService normalSignInService, NormalAccountService normalAccountService) {
        this.normalSignUpService = normalSignUpService;
        this.normalSignInService = normalSignInService;
        this.normalAccountService = normalAccountService;
    }

    @PostMapping(WebResourceConstant.NormalAccountCtrl.UPDATE_USER)
    public ResponseEntity<NormalUser> createUser(@RequestBody NormalUser normalUser){
        normalSignUpService.addNormalUser(normalUser);
        NormalUser returnedProfile = normalSignInService.getNormalUserByUsername(normalUser.getUsername());
        return new ResponseEntity<>(returnedProfile, HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.NormalAccountCtrl.UPDATE_PROFILE_PIC)
    public ResponseEntity<NormalProfilePic> updateProfilePic(@RequestBody PicDataDto picData)
            throws IOException {
        NormalProfilePic normalProfilePic1;
        NormalUser returnedUser = normalSignInService.getNormalUserByUsername(picData.getUsername());
        Long id = returnedUser.getId();

        byte[] decodedImg = Base64.getDecoder().decode(picData.getImage());
        //NormalProfilePic NormalProfilePic1 = new NormalProfilePic();

        String picPath = File.separator+"proinProjectNormalImages/"+picData.getUsername()+"."+picData.getFileType();

        FileOutputStream imageOutFile = new FileOutputStream(System.getProperty("catalina.home")+ picPath);

        try {
            imageOutFile.write(decodedImg);
        }
        catch(Exception e){
            System.out.println("error");
        }

        //IF ISEXISTS == TRUE
        NormalProfilePic normalProfilePic = normalAccountService.getUserPpByUsername(picData.getUsername());
        if(normalProfilePic != null) {
            Long trueId = normalProfilePic.getId();
            normalProfilePic1 = new NormalProfilePic(picData.getUsername(), picPath, returnedUser);
            normalProfilePic1.setId(trueId);
        }else {
            normalProfilePic1 = new NormalProfilePic(picData.getUsername(), picPath, returnedUser);
        }
        //

        normalAccountService.addProfilePic(normalProfilePic1);

        NormalProfilePic normalProfilePic2 = normalAccountService.getUserPpByUsername(picData.getUsername());

        return new ResponseEntity<NormalProfilePic>(normalProfilePic2, HttpStatus.ACCEPTED);
    }

    @PostMapping(WebResourceConstant.NormalAccountCtrl.FETCH_PROFILE_PIC)
    public ResponseEntity<NormalProfilePic> createUser(@RequestBody String username){
        NormalProfilePic normalProfilePic2 = normalAccountService.getUserPpByUsername(username);
        return new ResponseEntity<>(normalProfilePic2, HttpStatus.OK);
    }

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
        return new ResponseEntity<ValueDto>(valueDto,HttpStatus.OK);
    }

    // ******************************************************* //

    @PostMapping(WebResourceConstant.NormalAccountCtrl.SEARCH_PRO_USERS)
    public ResponseEntity<Void> searchProUsers(@RequestBody SearchParamsDto searchParams) {

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
