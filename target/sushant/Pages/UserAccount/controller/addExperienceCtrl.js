(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("AddExperienceController", addExperienceController);
    addExperienceController.$inject = ['$uibModalInstance','AddExperienceService'];

    function addExperienceController($uibModalInstance, AddExperienceService){

        var vm = this;
        vm.experience={id: null, username:'', title:'', company:'', location:'', startDate:'', endDate:'', description:''};

        vm.saveExperience = saveExperience;
        vm.cancelModal = cancelModal;
        var userData = localStorage['userInfo'];
        var user = JSON.parse(userData);
        getExperience();

        function getExperience() {
            AddExperienceService.getExperience(user.username)
                .then(
                    function(r) {
                        r.startDate = new Date(r.startDate);
                        r.endDate = new Date(r.endDate);
                        vm.experience = r;
                    },
                    function(errResponse){
                        alert('experience could not be retrieved');
                    });
        }

        function saveExperience() {
            vm.experience.username = user.username;
            //vm.experience.startDate = vm.experience.startDate.getTime();
            //vm.experience.endDate = vm.experience.endDate.getTime();
            console.log(vm.experience);
            AddExperienceService.sendExperience(vm.experience)
                .then(
                    function() {
                        cancelModal();
                    },
                    function(errResponse){
                        alert('this experience could not be saved');
                    });
        }

        function cancelModal(){
            $uibModalInstance.close('save');
        }


    };
})();
