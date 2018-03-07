'use strict';

angular
    .module('myApp')
    .factory('SearchResultsService', searchResultsService);

searchResultsService.$inject = ['HttpService'];

function searchResultsService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/searchResults/getCountries/';

    var factory = {
        getCountries: getCountries,
    };

    return factory;

    function getCountries() {
        return HttpService.get(REST_SERVICE_URI);
    }
}

