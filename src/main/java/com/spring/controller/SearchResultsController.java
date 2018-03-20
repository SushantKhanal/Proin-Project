package com.spring.controller;

import com.spring.requestDto.Countries;
import com.spring.model.User;
import com.spring.services.SearchResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<User>> listAllUsers(@PathVariable("country") String country, @PathVariable("searchTxt") String searchTxt) {

        List<User> results = searchResultsService.findResults(country, searchTxt);
        if(results.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(results, HttpStatus.OK);

    }

}
