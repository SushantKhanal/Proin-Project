(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("ShowAcademicsController", showAcademicsController);
    showAcademicsController.$inject = ['$uibModalInstance', 'AddAcademicsService'];

    function showAcademicsController($uibModalInstance, AddAcademicsService){

        var vm = this;
        vm.academics={id: null, username:'', degree:'', school:'', location:'', startDate:'', endDate:'', description:''};

        vm.cancelModal = cancelModal;
        var localUserData = localStorage['localOtherUser'];
        var user = JSON.parse(localUserData);
        getAcademics();

        function getAcademics() {
            AddAcademicsService.getAcademics(user.username)
                .then(
                    function(r) {
                        r.startDate = new Date(r.startDate);
                        r.endDate = new Date(r.endDate);
                        vm.academics = r;
                    },
                    function(errResponse){
                        alert('Academics could not be retrieved');
                    });
        }


        function cancelModal(){
            $uibModalInstance.close('save');
        }


    };
})();
