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
        getTags();

        function getTags() {
            AddTagsService.receiveTags()
                .then(
                    function(r) {
                        vm.tags = r.tags;
                    },
                    function(errResponse){
                        console.error('this review could not be saved');
                    });
        }

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
