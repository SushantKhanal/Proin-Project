'use strict';

angular
    .module('myApp')
    .factory('AdminAccountService', adminAccountService);

adminAccountService.$inject = ['HttpService'];

function adminAccountService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/admin/adminAccount/';

    var factory = {
        getCountries: getCountries,
    };

    return factory;

    function getCountries() {
        return HttpService.get(REST_SERVICE_URI + "getCountries/");
    }


}

