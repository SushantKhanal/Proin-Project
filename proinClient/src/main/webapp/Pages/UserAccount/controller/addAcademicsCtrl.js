(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("AddAcademicsController", addAcademicsController);
    addAcademicsController.$inject = ['$uibModalInstance', 'AddAcademicsService'];

    function addAcademicsController($uibModalInstance, AddAcademicsService){

        var vm = this;
        vm.academics={id: null, username:'', degree:'', school:'', location:'', startDate:'', endDate:'', description:''};

        vm.saveAcademics = saveAcademics;
        vm.cancelModal = cancelModal;
        var userData = localStorage['userInfo'];
        var user = JSON.parse(userData);


        function saveAcademics() {
            vm.academics.username = user.username;
            console.log(vm.academics);
            AddAcademicsService.sendAcademics(vm.academics)
                .then(
                    function() {
                        cancelModal();
                    },
                    function(errResponse){
                        alert('this Academics could not be saved');
                    });

        }

        function cancelModal(){
            $uibModalInstance.close('save');
        }


    };
})();
