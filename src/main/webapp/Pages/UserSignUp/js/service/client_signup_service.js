'use strict';

angular
    .module('myApp')
    .factory('ClientSignupService', clientSignupService);

clientSignupService.$inject = ['$http', '$q', 'HttpService'];

function clientSignupService($http, $q, HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/users/';

    var factory = {
        fetchAllUsers: fetchAllUsers,
        createUser: createUser,
    };

    return factory;

    function fetchAllUsers() {
        return HttpService.get(REST_SERVICE_URI);
    }

    function createUser(user) {
        return HttpService.post(REST_SERVICE_URI, user);
    }
}

