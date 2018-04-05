'use strict';

angular
    .module('myApp')
    .factory('SearchProClientsService', searchProClientsService);

searchProClientsService.$inject = ['HttpService'];

function searchProClientsService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/normalAccount/';

    var factory = {
        fetchValue: fetchValue,
        getCountries: getCountries,
        getMatchedProUsers: getMatchedProUsers,
    };

    return factory;

    function fetchValue() {
        return HttpService.get(REST_SERVICE_URI + "fetchValue/");
    }

    function getCountries() {
        return HttpService.get(REST_SERVICE_URI + "getCountries/");
    }

    function getMatchedProUsers(searchThis, country) {
        var searchParams = {
            searchThis: searchThis,
            country: country,
        }
        return HttpService.post(REST_SERVICE_URI + "getMatchedProUsers/", searchParams);
    }


}

