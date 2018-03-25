(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("AddExperienceController", addTagsController);
    addTagsController.$inject = ['$uibModalInstance','AddTagsService'];

    function addTagsController($uibModalInstance, AddTagsService){

        var vm = this;
        vm.experience={id: null, title:'', company:'', location:'', startDate:'', endDate:'', description:''};

        vm.saveExperience = saveExperience;
        vm.cancelModal = cancelModal;

        function saveExperience() {
            console.log(vm.experience);
        }

        function cancelModal(){
            $uibModalInstance.close('save');
        }


    };
})();
