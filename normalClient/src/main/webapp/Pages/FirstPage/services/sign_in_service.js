'use strict';

angular
    .module('myApp')
    .factory('SignInService', signInService);

signInService.$inject = ['$http', '$q', 'HttpService'];

function signInService($http, $q, HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/signIn/';

    var factory = {
        fetchNormalUser: fetchNormalUser,
    };

    return factory;

    function fetchNormalUser(username, password) {
        var signInInfo = {
            username: username,
            password: password,
        };
        return HttpService.post(REST_SERVICE_URI + 'fetchNormalUser/', signInInfo);
    }

}

