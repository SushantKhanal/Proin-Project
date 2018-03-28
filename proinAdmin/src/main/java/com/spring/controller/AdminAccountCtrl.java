package com.spring.controller;

import com.spring.responseDTO.CountriesList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */

@RestController
public class AdminAccountCtrl {

    @GetMapping("/adminAccount/getCountries/")
    public ResponseEntity<String[]> adminLogIn() {
        CountriesList countriesList = new CountriesList();
        String[] countries = countriesList.getCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

}
