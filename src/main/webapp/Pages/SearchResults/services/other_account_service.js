'use strict';

angular
    .module('myApp')
    .factory('OtherAccountService', otherAccountService);

otherAccountService.$inject = ['HttpService'];

function otherAccountService(HttpService) {

    var REST_SERVICE_URI = 'http://localhost:8080/searchResults/otherAccount/';

    var factory = {
        sendFavUser: sendFavUser,
        checkIfFav: checkIfFav,
        deleteFav: deleteFav,
    };

    return factory;

    function sendFavUser(loggedInUser, favUser) {

        var loggedFavUsers = {
            loggedInUser: loggedInUser,
            favUser: favUser
        };
        return HttpService.postFavUser(REST_SERVICE_URI, loggedFavUsers);
    }

    function checkIfFav(loggedInUser, favUser) {
        var loggedFavUsers = {
            loggedInUser: loggedInUser,
            favUser: favUser
        };
        return HttpService.postFavUser(REST_SERVICE_URI+'checkIfFav/', loggedFavUsers);
    }

    function deleteFav(loggedInUser, favUser) {

        var loggedFavUsers = {
            loggedInUser: loggedInUser,
            favUser: favUser
        };
        return HttpService.postFavUser(REST_SERVICE_URI+'deleteFav/', loggedFavUsers);
    }

}