(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("UploadDocController", uploadDocController);
    uploadDocController.$inject = ['UserAccountService','$uibModalInstance','$scope'];

    function uploadDocController(UserAccountService, $uibModalInstance, $scope){

        var vm = this;
        var userData, fileType, username;
        vm.cancelModal = cancelModal;
        vm.uploadDocument = uploadDocument;
        vm.documentSelected = '';
        // vm.showUploadImage = false;

        function cancelModal(){
            $uibModalInstance.close('save');
        }

        function uploadDocument() {
            userData = JSON.parse(localStorage['userInfo']);
            username = userData.username;
            fileType= vm.documentSelected.filetype.split("/",3)[1];
            postDoc(username, fileType, vm.documentSelected.base64)
        }

        //TAKES IMAGE TO BACKEND, POSTS PROFILE PIC
        function postDoc(username, fileType, doc){
            UserAccountService.postDoc(username, fileType, doc)
                .then(
                    function(d) {
                        //now update the actual profilePic
                        // console.log(d.picPath);
                        // var profilePicElement = document.getElementById('profile-image1');
                        // profilePicElement.setAttribute('ng-src', '/user' + d.picPath);
                        // //$scope.$apply();
                        // vm.cancelModal();
                    },
                    function(errResponse){
                        console.error('Error while updating doc');
                    }
                );
        }

    };
})();
