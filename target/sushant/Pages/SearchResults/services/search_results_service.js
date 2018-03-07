'use strict';

angular
    .module('myApp')
    .factory('SearchResultsService', searchResultsService);

searchResultsService.$inject = ['HttpService'];

function searchResultsService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/searchResults/';

    var factory = {
        getCountries: getCountries,
        getMatchedUsers: getMatchedUsers,
    };

    return factory;

    function getCountries() {
        return HttpService.get(REST_SERVICE_URI + "getCountries/");
    }

    function getMatchedUsers(searchTxt, country) {
        return HttpService.getSearchResults(REST_SERVICE_URI, searchTxt, country);
    }

}

