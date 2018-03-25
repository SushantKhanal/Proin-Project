'use strict';

angular
    .module('myApp')
    .factory('AddExperienceService', addExperienceService);

addExperienceService.$inject = ['HttpService'];

function addExperienceService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/user/';

    var factory = {
        sendExperience: sendExperience,
        getExperience: getExperience,
    };

    return factory;

    function getExperience(username) {
        return HttpService.post(REST_SERVICE_URI+'getExperience/', username);
    }

    function sendExperience(userExperience) {

        return HttpService.post(REST_SERVICE_URI+'userExperience/', userExperience);
    }


}

