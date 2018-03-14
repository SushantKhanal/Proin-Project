(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("ProfilePicController", profilePicController);
    profilePicController.$inject = ['$uibModalInstance', '$scope'];

    function profilePicController($uibModalInstance, $scope){

        var vm = this;
        var userData, username;
        vm.cancelModal = cancelModal;
        vm.uploadImage = uploadImage;
        vm.imageSelected = '';
        vm.showUploadImage = false;

        function cancelModal(){
            $uibModalInstance.close('save');
        }

        function uploadImage() {
            vm.showUploadImage = true;
            userData = JSON.parse(localStorage['userInfo']);
            username = userData.username;
            //postProfilePic(username, vm.imageSelected)
        }

        //TAKES IMAGE TO BACKEND, POSTS PROFILE PIC
        function postProfilePic(username, image){
            ProfilePicService.postProfilePic(username, image)
                .then(
                    function(d) {
                        //now update the actual profilePic
                        vm.cancelModal();
                    },
                    function(errResponse){
                        console.error('Error while updating Profile Pic');
                    }
                );
        }

    };
})();
