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
        vm.ignoreRequest = ignoreRequest;
        vm.showIgnoredRequests = showIgnoredRequests;
        vm.showIgnored = false;
        vm.ignoreRB = "Show Ignore Requests";
        checkFollowRequests();

        //follow request sent status = 0
        //follow request accepted status = 1
        //unfollowed status = 2
        //ignored request status = 3

        function showIgnoredRequests() {
            vm.showIgnored = !vm.showIgnored;
            if(vm.showIgnored == true) {
                vm.ignoreRB = "Hide Ignore Requests";
                UserAccountService.showIgnoredRequests()
                    .then(
                        function(ir) {
                            console.log(ir);
                            vm.ignoredRequests = ir;
                            //cancelModal();
                        },
                        function(errResponse){
                            console.log('Error');
                        });
            } else {
                vm.ignoreRB = "Show Ignore Requests";
            }

        }

        function approveRequest(id) {
            UserAccountService.acceptFollowRequest(id)
                .then(
                    function(r) {
                        console.log("Follow request approved");
                        cancelModal();
                    },
                    function(errResponse){
                        console.log('Error accepting request');
                    });
        }

        function ignoreRequest(id) {
            UserAccountService.ignoreFollowRequest(id)
                .then(
                    function(r) {
                        console.log("Follow request ignored");
                        cancelModal();
                    },
                    function(errResponse){
                        console.log('Error ignoring request');
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
