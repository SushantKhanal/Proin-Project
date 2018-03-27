//THIS IS THE SERVICE THAT TAKES THE ADMIN'S USERNAME AND PASSWORD TO BACKEND, AND SENDS BACK TO
//THE CONTROLLER WHATEVER IS RETURNED FROM BACKEND

'use strict';

angular
    .module('myApp')
    .factory('AdminSignInService', adminSignInService);

adminSignInService.$inject = ['HttpService'];
function adminSignInService(HttpService) {

    var REST_SERVICE_URI = 'http://localhost:8080/admin/adminLogIn/';

    var factory = {
        checkLogIn: checkLogIn,
    };

    return factory;


    function checkLogIn(uname, upass) {

        var userNamePassword = {
            username: uname,
            password: upass
        };

        return HttpService.post(REST_SERVICE_URI, userNamePassword);
    }

}

