package com.spring.controller;


import com.spring.dto.Countries;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchResultsController {

    //-------------------Retrieve Country names--------------------------------------------------------

    @GetMapping("/searchResults/getCountries/")
    public ResponseEntity<String[]> listAllUsers() {
        Countries Countries1 = new Countries();
        String[] countries = Countries1.getCountries();
        return new ResponseEntity<String[]>(countries, HttpStatus.OK);
    }

}
