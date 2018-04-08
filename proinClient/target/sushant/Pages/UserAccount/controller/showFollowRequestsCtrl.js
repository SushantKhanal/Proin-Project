(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("ShowFollowRequestsController", showFollowRequestsController);
    showFollowRequestsController.$inject = ['$uibModalInstance', 'UserAccountService'];

    function showFollowRequestsController($uibModalInstance, UserAccountService){

        var vm = this;
        var userData = '';
        var userData = localStorage['userInfo'];
        vm.user = JSON.parse(userData);
        vm.cancelModal = cancelModal;
        vm.approveRequest = approveRequest;
        checkFollowRequests();

        function approveRequest(id) {
            UserAccountService.acceptFollowRequest(id)
                .then(
                    function(r) {
                        console.log("Follow request approved");
                    },
                    function(errResponse){
                        console.log('Error accepting request');
                    });
        }

        function checkFollowRequests() {
            UserAccountService.checkFollowRequests(vm.user.username)
                .then(
                    function(r) {
                        vm.requests = r;
                    },
                    function(errResponse){
                        console.log('No follow requests');
                    });
        }

        function cancelModal(){
            $uibModalInstance.close('save');
        }


    };
})();
