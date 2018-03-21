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
        sendReview: sendReview,
        getReviews: getReviews,
    };

    return factory;

    function getReviews (otherUsername) {
        return HttpService.post(REST_SERVICE_URI+'getReviews/', otherUsername);
    }

    function sendReview (loggedInUsername, otherUsername, review, rating) {
        var loggedReview = {
            loggedInUsername: loggedInUsername,
            otherUsername: otherUsername,
            review: review,
            rating: rating
        };
        return HttpService.postObject(REST_SERVICE_URI+'review/', loggedReview);
    }

    function sendFavUser(loggedInUser, favUser) {

        var loggedFavUsers = {
            loggedInUser: loggedInUser,
            favUser: favUser
        };
        return HttpService.postObject(REST_SERVICE_URI, loggedFavUsers);
    }

    function checkIfFav(loggedInUser, favUser) {
        var loggedFavUsers = {
            loggedInUser: loggedInUser,
            favUser: favUser
        };
        return HttpService.postObject(REST_SERVICE_URI+'checkIfFav/', loggedFavUsers);
    }

    function deleteFav(loggedInUser, favUser) {

        var loggedFavUsers = {
            loggedInUser: loggedInUser,
            favUser: favUser
        };
        return HttpService.postObject(REST_SERVICE_URI+'deleteFav/', loggedFavUsers);
    }

}