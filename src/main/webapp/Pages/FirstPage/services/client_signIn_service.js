'use strict';

angular
    .module('myApp')
    .factory('ClientSignInService', clientSignInService);

clientSignInService.$inject = ['HttpService'];
function clientSignInService(HttpService) {

    var REST_SERVICE_URI = 'http://localhost:8080/userLogIn/';

    var factory = {
        checkLogIn: checkLogIn,
        response: {},
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

        // var deferred = $q.defer();
        // $http.post(REST_SERVICE_URI, userNamePassword)
        //     .then(
        //         function (response) {
        //
        //             deferred.resolve(response.data);
        //         },
        //         function(errResponse){
        //             console.error('Error while creating User');
        //             deferred.reject(errResponse);
        //         }
        //     );
        // return deferred.promise;

        return HttpService.post(REST_SERVICE_URI, userNamePassword);
    }

}

