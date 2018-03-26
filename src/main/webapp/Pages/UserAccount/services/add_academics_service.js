'use strict';

angular
    .module('myApp')
    .factory('AddAcademicsService', addAcademicsService);

addAcademicsService.$inject = ['HttpService'];

function addAcademicsService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/user/';

    var factory = {
        sendAcademics: sendAcademics,
        getAcademics: getAcademics,
    };

    return factory;

    function getAcademics(username) {
        return HttpService.post(REST_SERVICE_URI+'getAcademics/', username);
    }

    function sendAcademics(userAcademics) {

        return HttpService.post(REST_SERVICE_URI+'userAcademics/', userAcademics);
    }


}

