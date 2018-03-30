(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("FavouritesController", favouritesController);
    favouritesController.$inject = ['$uibModalInstance','$location','FavouritesService', 'ClientAccountService'];

    function favouritesController($uibModalInstance, $location, FavouritesService, ClientAccountService){

        var vm = this;
        var userData = '';
        vm.cancelModal = cancelModal;
        vm.showProfile = showProfile;
        vm.users;
        loadFavUsers();

        function showProfile(username) {
            console.log(username);
            FavouritesService.getUserProfile(username)
                .then(
                    function(d) {
                        vm.user = d;
                        localStorage['adminSeesClient'] = JSON.stringify(vm.user);
                        vm.cancelModal();
                        $location.path('/adminAccount/clientAccount/differentPath');
                    },
                    function(errResponse){
                        console.error('Error while fetching fav user names');
                    }
                );
        }

        function cancelModal(){
            $uibModalInstance.close('save');
        }

        function loadFavUsers() {
            var userData = localStorage['adminSeesClient'];

            if(userData !== undefined) {
                vm.user = JSON.parse(userData);
            }
            loadFavUsernames(vm.user.username);
        }
        function loadFavUsernames(username) {
            ClientAccountService.receiveFavs(username)
                .then(
                    function(d) {
                        vm.users = d;
                        console.log(vm.users);
                    },
                    function(errResponse){
                        console.error('Error while fetching fav user names');
                    }
                );
        }

    };
})();
