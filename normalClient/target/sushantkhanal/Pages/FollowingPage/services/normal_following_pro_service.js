'use strict';

angular
    .module('myApp')
    .factory('NormalFollowingProService', normalFollowingProService);

normalFollowingProService.$inject = ['HttpService'];

function normalFollowingProService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/normalAccount/';

    var factory = {
        fetchFollowings: fetchFollowings,
    };

    return factory;

    function fetchFollowings(username) {
        return HttpService.post(REST_SERVICE_URI + 'fetchFollowings/', username);
    }

}

