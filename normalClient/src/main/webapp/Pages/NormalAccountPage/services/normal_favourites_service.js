'use strict';

angular
    .module('myApp')
    .factory('NormalFavouritesService', normalFavouritesService);

normalFavouritesService.$inject = ['HttpService'];

function normalFavouritesService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/normalAccount/';

    var factory = {
        loadFavUsernames: loadFavUsernames,
    };

    return factory;

    function loadFavUsernames(username) {
        return HttpService.post(REST_SERVICE_URI + 'loadFavUsernames/', username);
    }

}

