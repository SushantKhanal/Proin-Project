'use strict';

angular
    .module('myApp')
    .factory('ProfilePicService', profilePicService);

profilePicService.$inject = ['HttpService'];

function profilePicService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/admin/api/adminAccount/';
    var factory = {
        postProfilePic: postProfilePic,
        getProfilePic: getProfilePic,
    };

    return factory;

    function postProfilePic(username, fileType, image) {
        return HttpService.postProfilePic(REST_SERVICE_URI + 'profilePic/', username, fileType, image);
    }

    function getProfilePic(username) {
        return HttpService.post(REST_SERVICE_URI + 'getProfilePic/', username);
    }
}

