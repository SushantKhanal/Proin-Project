'use strict';

angular
    .module('myApp')
    .factory('AddTagsService', addTagsService);

addTagsService.$inject = ['HttpService'];

function addTagsService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/user/';

    var factory = {
        sendTags: sendTags,
    };

    return factory;

    function sendTags(tags) {
        return HttpService.post(REST_SERVICE_URI+'sendTags/', tags);
    }


}

