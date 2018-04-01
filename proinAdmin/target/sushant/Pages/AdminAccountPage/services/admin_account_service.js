'use strict';

angular
    .module('myApp')
    .factory('AdminAccountService', adminAccountService);

adminAccountService.$inject = ['HttpService'];

function adminAccountService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/admin/api/adminAccount/';

    var factory = {
        getCountries: getCountries,
        getMatchedClients: getMatchedClients,
        getUserProfile: getUserProfile,
        sendEmail: sendEmail,
        fetchAccountRequests: fetchAccountRequests,
        approveClientRequest: approveClientRequest,
        denyClientRequest: denyClientRequest,
    };

    return factory;
    
    function approveClientRequest(username) {
        return HttpService.post(REST_SERVICE_URI + "approveClientRequest/", username);
    }
    
    function denyClientRequest(username) {
        return HttpService.post(REST_SERVICE_URI + "denyClientRequest/", username);
    }

    function fetchAccountRequests() {
        return HttpService.get(REST_SERVICE_URI + "clientAccountRequests/");
    }

    function sendEmail() {
        var myMail = "dreamerking114@gmail.com";
        return HttpService.post(REST_SERVICE_URI + "sendEmail/", myMail);
    }

    function getUserProfile(username) {
        return HttpService.post(REST_SERVICE_URI + "getClientProfile/", username);
    }

    function getCountries() {
        return HttpService.get(REST_SERVICE_URI + "getCountries/");
    }

    function getMatchedClients(searchThis, country, status) {
        var searchInfo = {
            searchThis: searchThis,
            country: country,
            status: status,
        };
        return HttpService.post(REST_SERVICE_URI + "getResults/", searchInfo);

    }

}

