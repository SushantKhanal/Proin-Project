'use strict';

angular
    .module('myApp')
    .factory('ProfilePicService', profilePicService);

profilePicService.$inject = ['HttpService'];

function profilePicService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/normalAccount/';

    var factory = {
        postProfilePic: postProfilePic,
    };

    return factory;

    function postProfilePic(username, fileType, image) {
        var data = {
            username: username,
            fileType: fileType,
            image: image,
        };
        return HttpService.post(REST_SERVICE_URI + 'updateProfilePic/', data);
    }

}

