'use strict';

angular
    .module('myApp')
    .factory('UserAccountService', userAccountService);

userAccountService.$inject = ['HttpService'];

function userAccountService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/user/';

    var factory = {
        updateUser: updateUser,
        getProfilePic: getProfilePic,
    };

    return factory;

    function updateUser(user, id) {
        return HttpService.put(REST_SERVICE_URI, user, id);
    }

    function getProfilePic(username) {
        return HttpService.getProfilePic(REST_SERVICE_URI, username);
    }
}

