(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("ProfilePicController", profilePicController);
    profilePicController.$inject = ['ProfilePicService','$uibModalInstance', '$scope'];

    function profilePicController(ProfilePicService, $uibModalInstance, $scope){

        var vm = this;
        var userData, fileType, username;
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
            fileType= vm.imageSelected.filetype.split("/",3)[1];
            postProfilePic(username, fileType, vm.imageSelected.base64)
        }

        //TAKES IMAGE TO BACKEND, POSTS PROFILE PIC
        function postProfilePic(username, fileType, image){
            ProfilePicService.postProfilePic(username, fileType, image)
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
