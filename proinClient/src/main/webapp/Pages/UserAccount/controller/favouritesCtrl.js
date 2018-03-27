(function(){
    'use strict';
    angular
        .module("myApp")
        .controller("FavouritesController", favouritesController);
    favouritesController.$inject = ['$uibModalInstance','$location','FavouritesService'];

    function favouritesController($uibModalInstance, $location, FavouritesService){

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
                        localStorage['localOtherUser'] = JSON.stringify(vm.user);
                        vm.cancelModal();
                        $location.path('/searchResults/otherAccount');
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
                        console.log(vm.users);
                    },
                    function(errResponse){
                        console.error('Error while fetching fav user names');
                    }
                );
        }

    };
})();
