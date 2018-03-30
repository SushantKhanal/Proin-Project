package com.spring.controller;

import com.spring.model.User;
import com.spring.requestDTO.SearchInfo;
import com.spring.responseDTO.CountriesList;
import com.spring.services.AdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminAccountCtrl {

    @Autowired
    AdminAccountService adminAccountService;

    @GetMapping("/adminAccount/getCountries/")
    public ResponseEntity<String[]> adminLogIn() {
        CountriesList countriesList = new CountriesList();
        String[] countries = countriesList.getCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @PostMapping("/adminAccount/getResults/")
    public ResponseEntity<List<String>> getResults(@RequestBody SearchInfo searchInfo) {
        String empty = "";
        String country = searchInfo.getCountry();
        String searchTxt = searchInfo.getSearchThis();
        Integer status = searchInfo.getStatus();

        if (country.equals(empty)){
            List<String> results = adminAccountService.getResults(searchTxt, status);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }

        List<String> results = adminAccountService.findResults(country, searchTxt, status);

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/adminAccount/getClientProfile/")
    public ResponseEntity<User> getClientProfile(@RequestBody String username) {

        User user = adminAccountService.getUserByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
