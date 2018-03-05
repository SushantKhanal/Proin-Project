'use strict';

angular
    .module('myApp')
    .factory('UserAccountService', userAccountService);

userAccountService.$inject = ['$http', '$q', 'HttpService'];

function userAccountService($http, $q, HttpService, ClientSignupService){

    var REST_SERVICE_URI = 'http://localhost:8080/user/';

    var factory = {
        updateUser: updateUser,
    };

    return factory;

    function updateUser(user, id) {
        return HttpService.put(REST_SERVICE_URI, user, id);
    }
}

