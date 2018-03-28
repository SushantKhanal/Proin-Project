'use strict';

angular
    .module('myApp')
    .factory('ClientAccountService', clientAccountService);

clientAccountService.$inject = ['HttpService'];

function clientAccountService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/admin/client/';

    var factory = {
        getProfilePic: getProfilePic,
        getReviews: getReviews,
        receiveTags: receiveTags,
        getAllExperience: getAllExperience,
        getAllAcademics: getAllAcademics,
        receiveFavs: receiveFavs,
    };

    return factory;

    function getAllAcademics(username) {
        return HttpService.post(REST_SERVICE_URI + "getAllAcademics/", username);
    }

    function getAllExperience(username) {
        return HttpService.post(REST_SERVICE_URI + "getAllExperience/", username);
    }

    function getProfilePic(username) {
        return HttpService.post(REST_SERVICE_URI + "getProfilePic/", username);
    }

    function getReviews(username) {
        return HttpService.post(REST_SERVICE_URI + "getReviews/", username);
    }

    function receiveTags(username) {
        return HttpService.post(REST_SERVICE_URI + "receiveTags/", username);
    }

    function receiveFavs(username) {
        return HttpService.post(REST_SERVICE_URI + "receiveFavs/", username);
    }

}

