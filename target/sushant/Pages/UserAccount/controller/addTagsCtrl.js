(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("AddTagsController", addTagsController);
    addTagsController.$inject = ['$uibModalInstance','AddTagsService'];

    function addTagsController($uibModalInstance, AddTagsService){

        var vm = this;
        vm.tags = '';
        vm.saveTags = saveTags;
        vm.cancelModal = cancelModal;

        function cancelModal(){
            $uibModalInstance.close('save');
        }

        function saveTags() {
            console.log(vm.tags);
            AddTagsService.sendTags(vm.tags);
            vm.cancelModal();
        }


    };
})();
