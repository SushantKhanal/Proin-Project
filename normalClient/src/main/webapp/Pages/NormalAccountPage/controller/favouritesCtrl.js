(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("FavouritesController", favouritesController);
    favouritesController.$inject = ['$uibModalInstance','$location', 'NormalFavouritesService', 'SearchProClientsService'];

    function favouritesController($uibModalInstance, $location, NormalFavouritesService, SearchProClientsService){

        var vm = this;
        var userData = '';
        vm.cancelModal = cancelModal;
        vm.showProfile = showProfile;
        vm.users;
        loadFavUsers();
        function showProfile(username) {
            console.log(username);
            SearchProClientsService.getProUserProfile(username)
                .then(
                    function(d) {
                        localStorage['localProUser'] = JSON.stringify(d);
                        cancelModal();
                        $location.path('/searchProClients/proAccount');
                    },
                    function(errResponse){
                        console.error('Error while fetching proAccount');
                    }
                );
        }

        function cancelModal(){
            $uibModalInstance.close('save');
        }

        function loadFavUsers() {
            var userData = localStorage['NormalUserInfo'];

            if(userData !== undefined) {
                vm.user = JSON.parse(userData);
            }
            loadFavUsernames(vm.user.username);
        }

        function loadFavUsernames(username) {
            NormalFavouritesService.loadFavUsernames(username)
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
