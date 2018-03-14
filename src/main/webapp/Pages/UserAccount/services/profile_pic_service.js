'use strict';

angular
    .module('myApp')
    .factory('ProfilePicService', profilePicService);

profilePicService.$inject = ['HttpService'];

function profilePicService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/profilePic/';

    var factory = {
        postProfilePic: postProfilePic,
    };

    return factory;

    function postProfilePic(username, image) {
        return HttpService.postProfilePic(REST_SERVICE_URI, username, image);
    }
}

