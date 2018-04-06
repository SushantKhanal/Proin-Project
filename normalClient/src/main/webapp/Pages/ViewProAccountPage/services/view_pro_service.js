'use strict';

angular
    .module('myApp')
    .factory('ProAccountService', proAccountService);

proAccountService.$inject = ['HttpService'];

function proAccountService(HttpService) {

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/viewProAccount/';

    var factory = {
        checkIfFollowed: checkIfFollowed,
        sendReview: sendReview,
        getReviews: getReviews,
        getProfilePic: getProfilePic,
        receiveTags: receiveTags,
        getAllExperience: getAllExperience,
        getAllAcademics: getAllAcademics,
        sendFav: sendFav,
        deleteFav: deleteFav,
        checkIfFav: checkIfFav,
    };

    return factory;

    function checkIfFollowed(loggedInUsername, otherUsername) {
        var ifFollowedData = {
            loggedInUsername: loggedInUsername,
            otherUsername: otherUsername,
        };
        return HttpService.post(REST_SERVICE_URI+'checkIfFollowed/', ifFollowedData);
    }

    function sendReview (loggedInUsername, otherUsername, review, rating) {
        var loggedReview = {
            loggedInUsername: loggedInUsername,
            otherUsername: otherUsername,
            review: review,
            rating: rating
        };
        return HttpService.post(REST_SERVICE_URI+'writeReview/', loggedReview);
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


    function sendFav(loggedInUser, favUser) {    //nth returns
        var loggedFavUsers = {
            loggedInNormalUser: loggedInUser,
            favProUser: favUser
        };
        return HttpService.post(REST_SERVICE_URI + 'sendFav/', loggedFavUsers);
    }


    function deleteFav(loggedInUser, favUser) {     //nth returns

        var loggedFavUsers = {
            loggedInNormalUser: loggedInUser,
            favProUser: favUser
        };
        return HttpService.post(REST_SERVICE_URI+'deleteFav/', loggedFavUsers);
    }

    function checkIfFav(loggedInUser, favUser) { //nth returns
        var loggedFavUsers = {
            loggedInNormalUser: loggedInUser,
            favProUser: favUser
        };
        return HttpService.post(REST_SERVICE_URI+'checkIfFav/', loggedFavUsers);
    }

}