package com.spring.controller;

import com.spring.model.User;
import com.spring.requestDTO.SearchInfo;
import com.spring.responseDTO.CountriesList;
import com.spring.services.AdminAccountService;
import com.spring.utils.WebResourceConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebResourceConstant.API_BASE +
        WebResourceConstant.AdminSetupCtrl.ADMIN_ACCOUNT_BASE)
public class AdminAccountCtrl {

    private final AdminAccountService adminAccountService;

    public AdminAccountCtrl(AdminAccountService adminAccountService) {
        this.adminAccountService = adminAccountService;
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

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PostMapping(WebResourceConstant.AdminSetupCtrl.DENY_CLIENT_REQUEST)
    public ResponseEntity<Void> denyClientRequest(@RequestBody String username) {

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
