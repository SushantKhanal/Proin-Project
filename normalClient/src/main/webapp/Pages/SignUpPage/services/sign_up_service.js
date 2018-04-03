'use strict';

angular
    .module('myApp')
    .factory('SignupService', signupService);

signupService.$inject = ['$http', '$q', 'HttpService'];

function signupService($http, $q, HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/signUp/';

    var factory = {
        createUser: createUser,
    };

    return factory;

    function createUser(user) {
        return HttpService.post(REST_SERVICE_URI + 'createUser/', user);
    }

}

