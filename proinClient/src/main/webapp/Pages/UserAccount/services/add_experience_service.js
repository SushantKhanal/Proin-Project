'use strict';

angular
    .module('myApp')
    .factory('AddExperienceService', addExperienceService);

addExperienceService.$inject = ['HttpService'];

function addExperienceService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/api/user/';

    var factory = {
        sendExperience: sendExperience,
        getAllExperience: getAllExperience,
        getExperienceFromId: getExperienceFromId,
        deleteExperience: deleteExperience,
    };

    return factory;

    function deleteExperience(id) {
        return HttpService.post(REST_SERVICE_URI+'deleteThisExperience/', id);
    }

    function getExperienceFromId(id) {
        return HttpService.post(REST_SERVICE_URI+'getExperienceFromId/', id);
    }

    function getAllExperience(username) {
        return HttpService.post(REST_SERVICE_URI+'getAllExperience/', username);
    }

    function sendExperience(userExperience) {

        return HttpService.post(REST_SERVICE_URI+'userExperience/', userExperience);
    }


}

