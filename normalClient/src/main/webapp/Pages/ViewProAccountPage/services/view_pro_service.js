'use strict';

angular
    .module('myApp')
    .factory('ProAccountService', proAccountService);

proAccountService.$inject = ['HttpService'];

function proAccountService(HttpService) {

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/viewProAccount/';

    var factory = {
        sendReview: sendReview,

        getReviews: getReviews,
        getProfilePic: getProfilePic,
        receiveTags: receiveTags,
        getAllExperience: getAllExperience,
        getAllAcademics: getAllAcademics,

        sendFav: sendFav,
        checkIfFav: checkIfFav,
        deleteFav: deleteFav,

    };

    return factory;

    function sendReview (loggedInUsername, otherUsername, review, rating) {
        // var loggedReview = {
        //     loggedInUsername: loggedInUsername,
        //     otherUsername: otherUsername,
        //     review: review,
        //     rating: rating
        // };
        // return HttpService.postObject(REST_SERVICE_URI+'writeReview/', loggedReview);
    }

    function getProfilePic(proUsername) {
        return HttpService.post(REST_SERVICE_URI+'getProProfilePic/', proUsername);
    }

    function receiveTags(proUsername) {
        return HttpService.post(REST_SERVICE_URI + 'receiveTags/', proUsername);
    }

    function getAllExperience(proUsername) {
        return HttpService.post(REST_SERVICE_URI + 'getAllExperience/', proUsername);
    }

    function getAllAcademics(proUsername) {
        return HttpService.post(REST_SERVICE_URI + 'getAllAcademics/', proUsername);
    }

    function getReviews (proUsername) {
        return HttpService.post(REST_SERVICE_URI+'getReviews/', proUsername);
    }

    function checkIfFav(loggedInUser, favUser) { //nth returns
        var loggedFavUsers = {
            loggedInUser: loggedInUser,
            favUser: favUser
        };
        return HttpService.post(REST_SERVICE_URI+'checkIfFav/', loggedFavUsers);
    }

    function sendFav(loggedInUser, favUser) {    //nth returns
        var loggedFavUsers = {
            loggedInUser: loggedInUser,
            favUser: favUser
        };
        return HttpService.post(REST_SERVICE_URI + 'sendFav/', loggedFavUsers);
    }


    function deleteFav(loggedInUser, favUser) {     //nth returns

        var loggedFavUsers = {
            loggedInUser: loggedInUser,
            favUser: favUser
        };
        return HttpService.postObject(REST_SERVICE_URI+'deleteFav/', loggedFavUsers);
    }

}