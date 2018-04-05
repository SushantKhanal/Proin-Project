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
        getProUserProfile: getProUserProfile,
    };

    return factory;

    function getProUserProfile(username) {
        return HttpService.post(REST_SERVICE_URI + 'getProUserProfile/', username)
    }

    function fetchValue() {
        return HttpService.get(REST_SERVICE_URI + "fetchValue/");
    }

    function getCountries() {
        return HttpService.get(REST_SERVICE_URI + "getCountries/");
    }

    function getMatchedProUsers(searchThis, country) {
        if(country == undefined) {
            country = "undefined";
        }
        var searchParams = {
            searchThis: searchThis,
            country: country,
        };
        return HttpService.post(REST_SERVICE_URI + "getMatchedProUsers/", searchParams);
    }

}

