package com.spring.controller;

import com.spring.model.*;
import com.spring.requestDTO.EmailContent;
import com.spring.requestDTO.SearchInfo;
import com.spring.responseDTO.CountriesList;
import com.spring.responseDTO.SearchResults;
import com.spring.scheduler.EmailSenderScheduler;
import com.spring.services.AdminAccountService;
import com.spring.services.AdminSignInService;
import com.spring.services.ClientAccountService;
import com.spring.utils.WebResourceConstant;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.AdminSetupCtrl.ADMIN_ACCOUNT_BASE)
public class AdminAccountCtrl {

    private final AdminAccountService adminAccountService;
    private final AdminSignInService adminSignInService;
    private final ClientAccountService clientAccountService;
    private final EmailSenderScheduler emailSenderScheduler;

    public AdminAccountCtrl(AdminAccountService adminAccountService, AdminSignInService adminSignInService,
                            ClientAccountService clientAccountService, EmailSenderScheduler emailSenderScheduler) {
        this.adminAccountService = adminAccountService;
        this.adminSignInService = adminSignInService;
        this.clientAccountService = clientAccountService;
        this.emailSenderScheduler = emailSenderScheduler;
    }

    @PostMapping(WebResourceConstant.AdminSetupCtrl.GET_PROFILE_PIC)
    public ResponseEntity<AdminProfilePic> getprofilePic(@RequestBody String username) {
        AdminProfilePic returnedAdminProfilePic = adminAccountService.getAdminPpByUsername(username);
        if(returnedAdminProfilePic.getId() != null) {
            return new ResponseEntity<AdminProfilePic>(returnedAdminProfilePic, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping(WebResourceConstant.AdminSetupCtrl.POST_PROFILE_PIC)
    public ResponseEntity<AdminProfilePic> postProfilePic(@PathVariable("username") String username,
                                               @PathVariable("fileType") String fileType,
                                               @RequestBody String image)
    throws IOException {
        Admin returnedAdmin = adminSignInService.getAdminByUsername(username);
        Long id = returnedAdmin.getId();

        byte[] decodedImg = Base64.getDecoder().decode(image);
        AdminProfilePic adminProfilePic1 = new AdminProfilePic();

        String picPath = File.separator+"proinProjectAdminImages/"+username+"."+fileType;

        FileOutputStream imageOutFile = new FileOutputStream(System.getProperty("catalina.home")+ picPath);

        try {
            imageOutFile.write(decodedImg);
        }
        catch(Exception e){
            System.out.println("error");
        }

        //IF ISEXISTS == TRUE
        AdminProfilePic adminProfilePic = adminAccountService.getAdminPpByUsername(username);
        if(adminProfilePic.getId() != null) {
            Long trueId = adminProfilePic.getId();
            adminProfilePic1 = new AdminProfilePic(username, picPath, returnedAdmin);
            adminProfilePic1.setId(trueId);
        }else {
            adminProfilePic1 = new AdminProfilePic(username, picPath, returnedAdmin);
        }

        adminAccountService.addProfilePic(adminProfilePic1);

        AdminProfilePic adminProfilePic2 = adminAccountService.getAdminPpByUsername(username);


        return new ResponseEntity<AdminProfilePic>(adminProfilePic2, HttpStatus.ACCEPTED);

    }

    @GetMapping(WebResourceConstant.AdminSetupCtrl.FETCH_COUNTRIES)
    public ResponseEntity<String[]> getCountries() {
        CountriesList countriesList = new CountriesList();
        String[] countries = countriesList.getCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    //THE FOLLOWING METHOD SHOULD FETCH CLIENT SIGN UP REQUESTS
    @GetMapping(WebResourceConstant.AdminSetupCtrl.CLIENT_ACCOUNT_REQUESTS)
    public ResponseEntity<List<String>> clientAccountRequests() {
        return new ResponseEntity<>(adminAccountService.getAllSignUpRequestUsernames(), HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.AdminSetupCtrl.FETCH_SEARCH_RESULTS)
    public ResponseEntity<SearchResults> getResults(@RequestBody SearchInfo searchInfo, Pageable pageable) {
        String empty = "";
        String country = searchInfo.getCountry();
        String searchTxt = searchInfo.getSearchThis();
        Integer status = searchInfo.getStatus();

        if (country.equals(empty)) {
            SearchResults results = adminAccountService.getResults(searchTxt, status, pageable);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }

        SearchResults results = adminAccountService.findResults(country, searchTxt, status, pageable);

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.AdminSetupCtrl.FETCH_CLIENT_PROFILE)
    public ResponseEntity<User> getClientProfile(@RequestBody String username) {
        return new ResponseEntity<>(adminAccountService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.AdminSetupCtrl.APPROVE_CLIENT_REQUEST)
    public ResponseEntity<Void> approveClientRequest(@RequestBody String username) {
        //fetch the signUp request
        UserSignUpRequest uR = adminAccountService.getSignUpRequestByUsername(username);
        //add the profile to users_table
        User user = new User(null, uR.getFirstName(), uR.getLastName(), uR.getDob(), uR.getBio(),
                uR.getNation(), uR.getClientType(), uR.getUsername(), uR.getPassword(),
                uR.getAddress(), uR.getEmail(), uR.getAgenda(), uR.getJoinDate(),
                uR.getAcademics(), uR.getExperience(), uR.getMarketDomain());
        adminAccountService.addUser(user);

        //add user status
        User returnedUser = adminAccountService.getUserByUsername(username);
        UserStatus userStatus = new UserStatus(1, username, returnedUser);
        clientAccountService.addUserStatus(userStatus);

        //send user a happy mail
        EmailContent email = new EmailContent("About Proin app Sign Up",
                "Your proin app sign up was successful, you can now log in",
                uR.getEmail());
        emailSenderScheduler.sendEmailToClient(email);

        //add status 0 to User Sign Up Request
        UserSignUpRequestStatus uSRS = new UserSignUpRequestStatus(uR.getId(), 0, username, uR);
        adminAccountService.addUserSignUpRequestStatus(uSRS);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.AdminSetupCtrl.DENY_CLIENT_REQUEST)
    public ResponseEntity<Void> denyClientRequest(@RequestBody String username) {
        UserSignUpRequest uR = adminAccountService.getSignUpRequestByUsername(username);
        //send denial email
        EmailContent email = new EmailContent("About Proin app Sign Up",
                "Sorry, your sign up request was denied",
                uR.getEmail());
        emailSenderScheduler.sendEmailToClient(email);

        //add status 0 to UserSignUp Request
        UserSignUpRequestStatus uSRS = new UserSignUpRequestStatus(uR.getId(), 0, username, uR);
        adminAccountService.addUserSignUpRequestStatus(uSRS);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.AdminSetupCtrl.FETCH_REQUESTING_CLIENT_INFO)
    public ResponseEntity<UserSignUpRequest> getRequestingUserInfo(@RequestBody String username) {
        return new ResponseEntity<UserSignUpRequest>(adminAccountService.getSignUpRequestByUsername(username),
                HttpStatus.OK);
    }

    @GetMapping(WebResourceConstant.AdminSetupCtrl.FETCH_ADMIN_REQUESTS)
    public ResponseEntity<List<String>> fetchAdminRequests() {
        //send list of admin accounts where status = 0;
        return new ResponseEntity<>(adminAccountService.getAdminRequests(), HttpStatus.OK);
    }

    @PostMapping(WebResourceConstant.AdminSetupCtrl.APPROVE_ADMIN_REQUEST)
    public ResponseEntity<Void> approveAdminRequests(@RequestBody String username) {
        adminAccountService.approveAdminRequest(username);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
//ADMIN STATUS 0 == NEITHER ACCEPTED NOR REJECTED
//ADMIN STATUS 1 == ACCEPTED
//ADMIN STATUS 2 == REJECTED
    @PostMapping(WebResourceConstant.AdminSetupCtrl.REJECT_ADMIN_REQUEST)
    public ResponseEntity<Void> rejectAdminRequest(@RequestBody String username) {
        adminAccountService.rejectAdminRequest(username);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
