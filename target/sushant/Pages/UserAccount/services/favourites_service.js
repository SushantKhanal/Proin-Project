'use strict';

angular
    .module('myApp')
    .factory('FavouritesService', favouritesService);

favouritesService.$inject = ['HttpService'];

function favouritesService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/user/getFavUsers/';

    var factory = {
        loadFavUsernames: loadFavUsernames,
    };

    return factory;

    function loadFavUsernames(username) {
        return HttpService.post(REST_SERVICE_URI, username);
    }

}

