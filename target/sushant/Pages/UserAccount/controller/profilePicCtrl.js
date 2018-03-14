(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("ProfilePicController", profilePicController);
    profilePicController.$inject = ['$uibModalInstance', '$scope'];

    function profilePicController($uibModalInstance, $scope){
        var vm = this;
        vm.cancelModal = cancelModal;
        vm.uploadImage = uploadImage;
        vm.imageSelected = '';
        vm.showUploadImage = false;
        function cancelModal(){
            $uibModalInstance.close('save');
        }
        function uploadImage() {
            vm.showUploadImage = true;
            var userData = localStorage['userInfo'];
            userData = JSON.parse(userData);
            console.log(userData.username);
        }

    };
})();
