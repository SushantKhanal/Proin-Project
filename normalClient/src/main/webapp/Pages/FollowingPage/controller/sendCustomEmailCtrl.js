(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("SendCustomEmailController", sendCustomEmailController);
    sendCustomEmailController.$inject = ['$uibModalInstance','$location', 'CustomEmailService', 'param'];

    function sendCustomEmailController($uibModalInstance, $location, CustomEmailService, param){

        var vm = this;
        vm.cancelModal = cancelModal;
        vm.sendEmail = sendEmail;

        function cancelModal(){
            $uibModalInstance.close('save');
        }

        function sendEmail() {
            CustomEmailService.sendEmail(param, vm.emailSubject, vm.emailBody)
                .then(function(){
                    cancelModal();
                }, function(){
                    alert("error sending follow request");
                })
        }

    }
})();
