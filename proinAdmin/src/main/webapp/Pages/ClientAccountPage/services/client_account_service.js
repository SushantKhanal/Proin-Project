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
        deleteThisAccount: deleteThisAccount,
        checkIfDeleted: checkIfDeleted,
        undoDeleteThisAccount: undoDeleteThisAccount,
    };

    return factory;
    
    function checkIfDeleted(username) {
        return HttpService.post(REST_SERVICE_URI + "checkIfDeleted/", username);
    }

    function deleteThisAccount(username) {
        return HttpService.post(REST_SERVICE_URI + "deleteThisAccount/", username);
    }

    function undoDeleteThisAccount(username) {
        return HttpService.post(REST_SERVICE_URI + "undoDeleteThisAccount/", username);
    }

    function getAllAcademics(username) {
        return HttpService.post(REST_SERVICE_URI + "getAllAcademics/", username);
    }

    function getAllExperience(username) {
        return HttpService.post(REST_SERVICE_URI + "getAllExperience/", username);
    }

    function getProfilePic(username) {
        return HttpService.post(REST_SERVICE_URI + "getProfilePic/", username);
    }

    function getReviews(pageNumber, username) {
        // return HttpService.post(REST_SERVICE_URI +
        //     "getResults?page="+pageNumber+"&size="+size, searchInfo);
        return HttpService.post(REST_SERVICE_URI + "getReviews?page="+pageNumber+"&size="+2, username);
        //keep the size even, for data is being fetched from two different tables
    }

    function receiveTags(username) {
        return HttpService.post(REST_SERVICE_URI + "receiveTags/", username);
    }

    function receiveFavs(username) {
        return HttpService.post(REST_SERVICE_URI + "receiveFavs/", username);
    }

}

