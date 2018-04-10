'use strict';

angular
    .module('myApp')
    .factory('AdminSignUpService', adminSignupService);

adminSignupService.$inject = ['$http', '$q', 'HttpService'];

function adminSignupService($http, $q, HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/admin/api/SignUp/';

    var factory = {
        createAdmin: createAdmin,
    };

    return factory;

    function createAdmin(admin) {
        return HttpService.post(REST_SERVICE_URI + 'createAdmin/', admin);
    }

}

