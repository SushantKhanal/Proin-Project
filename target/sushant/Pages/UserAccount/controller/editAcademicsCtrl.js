(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("EditAcademicsController", editAcademicsController);
    editAcademicsController.$inject = ['$uibModalInstance', 'AddAcademicsService'];

    function editAcademicsController($uibModalInstance, AddAcademicsService){

        var vm = this;
        vm.academics={id: null, username:'', degree:'', school:'', location:'', startDate:'', endDate:'', description:''};

        vm.saveAcademics = saveAcademics;
        vm.deleteAcademics = deleteAcademics;
        var userData = localStorage['userInfo'];
        var academicsData = localStorage['academicsToBeEdited'];
        var user = JSON.parse(userData);
        getAcademics();

        function deleteAcademics(){
            alert("are you sure you want to delete " + vm.academics.degree + " from your profile?");
            AddAcademicsService.deleteAcademics(vm.academics.id)
                .then(
                    function() {
                        localStorage['academicsToBeEdited'] = undefined;
                        cancelModal();
                    },
                    function(errResponse){

                    });
        }

        function getAcademics() {
            vm.academics = JSON.parse(academicsData);
            vm.academics.startDate = new Date(vm.academics.startDate);
            vm.academics.endDate = new Date(vm.academics.endDate);
        }

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
