package com.spring.controller;

import com.spring.model.User;
import com.spring.model.UserSignUpRequest;
import com.spring.model.UserStatus;
import com.spring.requestDTO.EmailContent;
import com.spring.requestDTO.SearchInfo;
import com.spring.responseDTO.CountriesList;
import com.spring.scheduler.EmailSenderScheduler;
import com.spring.services.AdminAccountService;
import com.spring.services.ClientAccountService;
import com.spring.utils.WebResourceConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.AdminSetupCtrl.ADMIN_ACCOUNT_BASE)
public class AdminAccountCtrl {

    private final AdminAccountService adminAccountService;
    private final ClientAccountService clientAccountService;
    private final EmailSenderScheduler emailSenderScheduler;

    public AdminAccountCtrl(AdminAccountService adminAccountService, ClientAccountService clientAccountService,
                            EmailSenderScheduler emailSenderScheduler) {
        this.adminAccountService = adminAccountService;
        this.clientAccountService = clientAccountService;
        this.emailSenderScheduler = emailSenderScheduler;
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
    public ResponseEntity<List<String>> getResults(@RequestBody SearchInfo searchInfo) {
        String empty = "";
        String country = searchInfo.getCountry();
        String searchTxt = searchInfo.getSearchThis();
        Integer status = searchInfo.getStatus();

        if (country.equals(empty)) {
            List<String> results = adminAccountService.getResults(searchTxt, status);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }

        List<String> results = adminAccountService.findResults(country, searchTxt, status);

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
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
