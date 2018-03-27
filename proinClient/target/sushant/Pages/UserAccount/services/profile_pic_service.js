'use strict';

angular
    .module('myApp')
    .factory('ProfilePicService', profilePicService);

profilePicService.$inject = ['HttpService'];

function profilePicService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/user/profilePic/';

    var factory = {
        postProfilePic: postProfilePic,
    };

    return factory;

    function postProfilePic(username, fileType, image) {
        return HttpService.postProfilePic(REST_SERVICE_URI, username, fileType, image);
    }
}

