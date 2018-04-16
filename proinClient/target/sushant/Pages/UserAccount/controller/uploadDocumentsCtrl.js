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


        function cancelModal(){
            $uibModalInstance.close('save');
        }

        function uploadDocument() {
            userData = JSON.parse(localStorage['userInfo']);
            username = userData.username;
            fileType= vm.documentSelected.filetype.split("/",3)[1];
            var fileName = vm.documentSelected.filename.split('.')[0];
            // if(fileType.includes('.')){
            //     var fileType1 = fileType.split('.');
            //     fileType = fileType1[fileType1.length-1];
            // }
            postDoc(username, fileName, fileType, vm.documentSelected.base64)
        }

        //TAKES IMAGE TO BACKEND, POSTS PROFILE PIC
        function postDoc(username,fileName, fileType, doc){
            UserAccountService.postDoc(username, fileName, fileType, doc)
                .then(
                    function(d) {
                        vm.cancelModal();
                    },
                    function(errResponse){
                        console.error('Error while updating doc');
                    }
                );
        }

    };
})();
