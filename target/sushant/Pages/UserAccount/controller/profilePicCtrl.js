(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("ProfilePicController",['$uibModalInstance', profilePicController])

    function profilePicController($uibModalInstance){
        var vm = this;
        vm.cancelModal = cancelModal;
        vm.fileSelected;
        function cancelModal(){
            console.log("cancelmodal");
            $uibModalInstance.dismiss('close');
        }

    };
})();
