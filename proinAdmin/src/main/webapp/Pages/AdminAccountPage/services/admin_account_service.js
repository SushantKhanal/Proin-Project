'use strict';

angular
    .module('myApp')
    .factory('AdminAccountService', adminAccountService);

adminAccountService.$inject = ['HttpService'];

function adminAccountService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/admin/adminAccount/';

    var factory = {
        getCountries: getCountries,
        getMatchedClients: getMatchedClients,
        getUserProfile: getUserProfile,
    };

    return factory;

    function getUserProfile(username) {
        return HttpService.post(REST_SERVICE_URI + "getClientProfile/", username);
    }

    function getCountries() {
        return HttpService.get(REST_SERVICE_URI + "getCountries/");
    }

    function getMatchedClients(searchThis, country) {
        var searchInfo = {
            searchThis: searchThis,
            country: country
        };
        return HttpService.post(REST_SERVICE_URI + "getResults/", searchInfo);

    }

}

