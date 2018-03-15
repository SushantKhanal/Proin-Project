(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("ProfilePicController", profilePicController);
    profilePicController.$inject = ['ProfilePicService','$uibModalInstance'];

    function profilePicController(ProfilePicService, $uibModalInstance){

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
                        console.log(d.picPath);
                        var profilePicElement = document.getElementById('profile-image1');
                        profilePicElement.setAttribute('src', '/user' + d.picPath);
                        //profilePicElement.setAttribute('src', 'https://images.unsplash.com/photo-1507984211203-76701d7bb120?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=eacbf50fac29a13afba26ad7499cedee&auto=format&fit=crop&w=1352&q=80');
                        vm.cancelModal();
                    },
                    function(errResponse){
                        console.error('Error while updating Profile Pic');
                    }
                );

        }

    };
})();
