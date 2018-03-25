(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("AddExperienceController", addTagsController);
    addTagsController.$inject = ['$uibModalInstance','AddExperienceService'];

    function addTagsController($uibModalInstance, AddExperienceService){

        var vm = this;
        vm.experience={username:'', title:'', company:'', location:'', startDate:'', endDate:'', description:''};

        vm.saveExperience = saveExperience;
        vm.cancelModal = cancelModal;
        var userData = localStorage['userInfo'];
        var user = JSON.parse(userData);

        function saveExperience() {
            vm.experience.username = user.username;
            AddExperienceService.sendExperience(vm.experience);
            cancelModal();
        }

        function cancelModal(){
            $uibModalInstance.close('save');
        }


    };
})();
