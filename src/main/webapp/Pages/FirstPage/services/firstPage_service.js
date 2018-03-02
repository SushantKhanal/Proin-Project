'use strict';

angular
    .module('myApp')
    .factory('SignInService', ['$http', '$q', function($http, $q) {

        var REST_SERVICE_URI = 'http://localhost:8080/userLogIn/';

        var factory = {
            checkLogIn: checkLogIn,
        };

        return factory;

        function checkLogIn(uname, upass) {

            var userNamePassword = {
                username: uname,
                password: upass
            };
            var deferred = $q.defer();
            $http.post(REST_SERVICE_URI, userNamePassword)
                .then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse){
                        console.error('Error while creating User');
                        deferred.reject(errResponse);
                    }
                );
            return deferred.promise;
        }

    }]);