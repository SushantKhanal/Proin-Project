'use strict';

angular
    .module('myApp')
    .factory('OtherAccountService', otherAccountService);

otherAccountService.$inject = ['HttpService'];

function otherAccountService(HttpService) {

    var REST_SERVICE_URI = 'http://localhost:8080/searchResults/otherAccount';

    var factory = {
        sendFavUser: sendFavUser,
    };

    return factory;

    function sendFavUser(loggedInUser, selectedUser) {
        return HttpService.postFavUser(REST_SERVICE_URI, loggedInUser, selectedUser);
    }

}