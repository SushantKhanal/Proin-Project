(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("ShowExperienceController", showExperienceController);
    showExperienceController.$inject = ['$uibModalInstance','AddExperienceService'];

    function showExperienceController($uibModalInstance, AddExperienceService){

        var vm = this;
        vm.experience={id: null, username:'', title:'', company:'', location:'', startDate:'', endDate:'', description:''};

        vm.cancelModal = cancelModal;
        var localUserData = localStorage['localOtherUser'];
        var user = JSON.parse(localUserData);
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

        function cancelModal(){
            $uibModalInstance.close('save');
        }

    };
})();
