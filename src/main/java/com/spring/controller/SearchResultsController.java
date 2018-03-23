package com.spring.controller;

import com.spring.requestDto.Countries;
import com.spring.model.User;
import com.spring.services.SearchResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchResultsController {

    @Autowired
    private SearchResultsService searchResultsService;

    //-------------------Retrieve Country names--------------------------------------------------------

    @GetMapping("/searchResults/getCountries/")
    public ResponseEntity<String[]> listAllUsers() {
        Countries Countries1 = new Countries();
        String[] countries = Countries1.getCountries();
        return new ResponseEntity<String[]>(countries, HttpStatus.OK);
    }

    @GetMapping("/searchResults/{country}/{searchTxt}")
    public ResponseEntity<List<String>> listAllUsers(@PathVariable("country") String country, @PathVariable("searchTxt") String searchTxt) {
        String undefined = "undefined";
        if (country.equals(undefined)){
            List<String> results = searchResultsService.getResults(searchTxt);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        List<String> results = searchResultsService.findResults(country, searchTxt);

        return new ResponseEntity<>(results, HttpStatus.OK);

    }


}
