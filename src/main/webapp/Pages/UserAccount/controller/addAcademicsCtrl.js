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
        //getAcademics();

        // function getAcademics() {
        //     AddAcademicsService.getAcademics(user.username)
        //         .then(
        //             function(r) {
        //                 r.startDate = new Date(r.startDate);
        //                 r.endDate = new Date(r.endDate);
        //                 vm.Academics = r;
        //             },
        //             function(errResponse){
        //                 alert('Academics could not be retrieved');
        //             });
        // }

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
