'use strict';

angular
    .module('myApp')
    .factory('FollowRequestService', followRequestService);

followRequestService.$inject = ['HttpService'];

function followRequestService(HttpService) {

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/viewProAccount/';

    var factory = {
        sendRequest: sendRequest,
    };

    return factory;

    function sendRequest (fromNormalUsername, toProUsername, message) {
        var loggedMessage = {
            fromNormalUsername: fromNormalUsername,
            toProUsername: toProUsername,
            message: message,
        };
        return HttpService.post(REST_SERVICE_URI+'sendFollowRequest/', loggedMessage);
    }

}