'use strict';

angular
    .module('myApp')
    .factory('NormalFollowersService', normalFollowersService);

normalFollowersService.$inject = ['HttpService'];

    //follow request sent status = 0
    //follow request accepted status = 1
    //unfollowed status = 2
    //ignored request status = 3

function normalFollowersService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/api/NormalFollowers';

    var factory = {
        fetchFollowers: fetchFollowers,
    };

    return factory;

    function fetchFollowers(username) {
        return HttpService.post(REST_SERVICE_URI+'/fetchFollowers/', username);
    }

}

