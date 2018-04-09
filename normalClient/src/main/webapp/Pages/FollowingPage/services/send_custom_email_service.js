'use strict';

angular
    .module('myApp')
    .factory('CustomEmailService', customEmailService);

customEmailService.$inject = ['HttpService'];

function customEmailService(HttpService) {

    var REST_SERVICE_URI = 'http://localhost:8080/normalClient/api/sendCustomEmail/';

    var factory = {
        sendEmail: sendEmail,
    };

    return factory;

    function sendEmail (emailId, subject, body) {
        var loggedEmail = {
            emailId: emailId,
            subject: subject,
            body: body,
        };
        return HttpService.post(REST_SERVICE_URI+'sendCustomEmail/', loggedEmail);
    }

}
