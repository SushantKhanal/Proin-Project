'use strict';

angular
    .module('myApp')
    .factory('NormalAccountService', normalAccountService);

normalAccountService.$inject = ['HttpService'];

function normalAccountService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/normalAccount/';

    var factory = {
        updateUser: updateUser,
        getProfilePic: getProfilePic,
    };

    return factory;

    function updateUser(user) {
        return HttpService.post(REST_SERVICE_URI + 'updateUser/', user);
    }

    function getProfilePic(username) {
        return HttpService.post(REST_SERVICE_URI+'fetchProfilePic/', username);
    }
}

