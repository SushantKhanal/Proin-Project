'use strict';

angular
    .module('myApp')
    .factory('AddAcademicsService', addAcademicsService);

addAcademicsService.$inject = ['HttpService'];

function addAcademicsService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/api/user/';

    var factory = {
        sendAcademics: sendAcademics,
        getAllAcademics: getAllAcademics,
        getAcademicFromId: getAcademicFromId,
        deleteAcademics: deleteAcademics,
    };

    return factory;

    function getAllAcademics(username) {
        return HttpService.post(REST_SERVICE_URI+'getAllAcademics/', username);
    }

    function sendAcademics(userAcademics) {
        return HttpService.post(REST_SERVICE_URI+'userAcademics/', userAcademics);
    }

    function getAcademicFromId(id) {
        return HttpService.post(REST_SERVICE_URI+'getAcademicFromId/', id);

    }

    function deleteAcademics(id) {
        return HttpService.post(REST_SERVICE_URI+'deleteThisAcademics/', id);
    }
}

