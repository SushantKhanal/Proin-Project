(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("FavouritesController", favouritesController);
    favouritesController.$inject = ['$uibModalInstance','$scope','FavouritesService'];

    function favouritesController($uibModalInstance, $scope, FavouritesService){

        var vm = this;
        var userData = '';
        vm.cancelModal = cancelModal;
        vm.users;
        loadFavUsers();
        function cancelModal(){
            $uibModalInstance.close('save');
        }
        function loadFavUsers() {
            var userData = localStorage['userInfo'];

            if(userData !== undefined) {
                vm.user = JSON.parse(userData);
            }
            loadFavUsernames(vm.user.username);
        }
        function loadFavUsernames(username) {
            FavouritesService.loadFavUsernames(username)
                .then(
                    function(d) {
                        vm.users = d;
                    },
                    function(errResponse){
                        console.error('Error while fetching fav user names');
                    }
                );
        }

    };
})();
