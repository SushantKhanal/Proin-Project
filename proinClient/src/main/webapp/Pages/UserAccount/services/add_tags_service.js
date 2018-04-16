'use strict';

angular
    .module('myApp')
    .factory('AddTagsService', addTagsService);

addTagsService.$inject = ['HttpService'];

function addTagsService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/api/user/';

    var factory = {
        sendTags: sendTags,
        receiveTags: receiveTags,
    };

    return factory;

    function sendTags(tags) {
        var userData = localStorage['userInfo'];
        var user = JSON.parse(userData);
        var userTags = {
          username: user.username,
          tags: tags
        };
        return HttpService.post(REST_SERVICE_URI+'sendTags/', userTags);
    }

    function receiveTags(username){
        return HttpService.post(REST_SERVICE_URI+'receiveTags/', username);
    }


}

