'use strict';

angular
    .module('myApp')
    .factory('AddExperienceService', addExperienceService);

addExperienceService.$inject = ['HttpService'];

function addExperienceService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/user/';

    var factory = {
        sendExperience: sendExperience,
    };

    return factory;

    function sendExperience(userExperience) {

        return HttpService.post(REST_SERVICE_URI+'userExperience/', userExperience);
    }


}

