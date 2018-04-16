'use strict';

angular
    .module('myApp')
    .factory('UserAccountService', userAccountService);

userAccountService.$inject = ['HttpService'];

function userAccountService(HttpService){

    var REST_SERVICE_URI = 'http://localhost:8080/api/user/';

    var factory = {
        updateUser: updateUser,
        getProfilePic: getProfilePic,
        checkFollowRequests: checkFollowRequests,
        acceptFollowRequest: acceptFollowRequest,
        ignoreFollowRequest: ignoreFollowRequest,
        showIgnoredRequests: showIgnoredRequests,
        postDoc: postDoc,
        checkForUploadedDocs: checkForUploadedDocs,
    };

    return factory;

    function checkForUploadedDocs(username) {
        return HttpService.post(REST_SERVICE_URI + 'checkForUploadedDocs/', username)
    }

    function postDoc(username, fileName, fileType, doc) {
        var docInfo = {
            username,
            fileName,
            fileType,
            doc,
        };
        return HttpService.post(REST_SERVICE_URI + 'postDoc/', docInfo)
    }

    function showIgnoredRequests() {
        return HttpService.get(REST_SERVICE_URI + 'getIgnoredRequests/')
    }

    function acceptFollowRequest(id) {
        return HttpService.post(REST_SERVICE_URI + 'acceptFollowRequest/', id)
    }

    function ignoreFollowRequest(id) {
        return HttpService.post(REST_SERVICE_URI + 'ignoreFollowRequest/', id)
    }

    function checkFollowRequests(username) {
        return HttpService.post(REST_SERVICE_URI + 'checkFollowRequests/', username)
    }

    function updateUser(user, id) {
        return HttpService.put(REST_SERVICE_URI, user, id);
    }

    function getProfilePic(username) {
        return HttpService.getProfilePic(REST_SERVICE_URI, username);
    }

}

