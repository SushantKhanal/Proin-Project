//THIS IS THE SERVICE THAT TAKES THE CLIENT'S USERNAME AND PASSWORD TO BACKEND, AND SENDS BACK TO
//THE CONTROLLER WHATEVER IS RETURNED FROM BACKEND

'use strict';

angular
    .module('myApp')
    .factory('ClientSignInService', clientSignInService);

clientSignInService.$inject = ['HttpService'];
function clientSignInService(HttpService) {

    var REST_SERVICE_URI = 'http://localhost:8080/userLogIn/';

    var factory = {
        checkLogIn: checkLogIn,
        response: {}, //IN THIS VARIABLE THE SIGNED IN USER PROFILE IS STORED
        setResponse: setResponse,
        getResponse: getResponse,
    };

    return factory;

    function setResponse(response) {
        this.response = response;
    }

    function getResponse() {
        return this.response;
    }

    function checkLogIn(uname, upass) {

        var userNamePassword = {
            username: uname,
            password: upass
        };

        return HttpService.post(REST_SERVICE_URI, userNamePassword);
    }

}

