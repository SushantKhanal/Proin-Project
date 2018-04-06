(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("FollowRequestController", followRequestController);
    followRequestController.$inject = ['$uibModalInstance','$location', 'FollowRequestService'];

    function followRequestController($uibModalInstance, $location, FollowRequestService){

        var vm = this;
        vm.cancelModal = cancelModal;
        vm.sendRequest = sendRequest;

        function cancelModal(){
            $uibModalInstance.close('save');
        }

        function sendRequest() {
            console.log(vm.msg);
            var userData = localStorage['NormalUserInfo'];
            var loggedInUser = JSON.parse(userData);

            var localUserData = localStorage['localProUser'];
            var viewedProUser = JSON.parse(localUserData);
            FollowRequestService.sendRequest(loggedInUser.username, viewedProUser.username , vm.msg)
                .then(function(){
                    cancelModal();
                }, function(){
                    alert("error sending follow request");
                })
        }

    }
})();
