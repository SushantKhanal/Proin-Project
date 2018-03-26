(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("EditExperienceController", editExperienceController);
    editExperienceController.$inject = ['$uibModalInstance','AddExperienceService'];

    function editExperienceController($uibModalInstance, AddExperienceService){

        var vm = this;
        vm.experience={id: null, username:'', title:'', company:'', location:'', startDate:'', endDate:'', description:''};

        vm.saveExperience = saveExperience;
        vm.deleteExperience = deleteExperience;
        var userData = localStorage['userInfo'];
        var experienceData = localStorage['experienceToBeEdited'];
        var user = JSON.parse(userData);
        getExperience();

        function getExperience() {
            vm.experience = JSON.parse(experienceData);
            vm.experience.startDate = new Date(vm.experience.startDate);
            vm.experience.endDate = new Date(vm.experience.endDate);
        }

        function deleteExperience(){
            alert("are you sure you want to delete " + vm.experience.title + " from your profile?");
            AddExperienceService.deleteExperience(vm.experience.id)
                .then(
                    function() {
                        localStorage['experienceToBeEdited'] = undefined;
                        cancelModal();
                    },
                    function(errResponse){

                    });
        }
        
        function saveExperience() {
            vm.experience.username = user.username;
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
