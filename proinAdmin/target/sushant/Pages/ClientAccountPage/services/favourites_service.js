'use strict';

angular
    .module('myApp')
    .factory('FavouritesService', favouritesService);

favouritesService.$inject = ['HttpService'];

function favouritesService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/admin/';

    var factory = {
        loadFavUsernames: loadFavUsernames,
        getUserProfile: getUserProfile
    };

    return factory;

    function loadFavUsernames(loggedInUsername) {
        return HttpService.post(REST_SERVICE_URI+'client/receiveFavs/', loggedInUsername);
    }

    function getUserProfile(otherAccountUsername) {
        return HttpService.post(REST_SERVICE_URI+'adminAccount/getClientProfile/', otherAccountUsername);
    }

}

