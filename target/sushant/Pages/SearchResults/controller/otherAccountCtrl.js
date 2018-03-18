angular
    .module('myApp')
    .controller('OtherAccountCtrl', otherAccountController);

otherAccountController.$inject = ['$location','UserAccountService', 'OtherAccountService'];

function otherAccountController($location, UserAccountService, OtherAccountService) {
    var vm = this;

    vm.backToSearch = backToSearch;

    vm.picPath1 = '';

    vm.addToFav = addToFav;

    var localUserData = localStorage['localOtherUser'];

    if(localUserData !== undefined) {
        vm.user = JSON.parse(localUserData);
        getProfilePic(vm.user.username);
    }

    function addToFav() {
        $('.makeFav').toggleClass('redBackground');
        if ($(".makeFav").hasClass("redBackground")){
            sendFav();
        }
    }

    function sendFav() {
        var userData = localStorage['userInfo'];
        var loggedInUser = JSON.parse(userData);
        var favUser = JSON.parse(localUserData);
        OtherAccountService.sendFavUser(loggedInUser, favUser);
    }

    function backToSearch() {
        $location.path('/userAccount/searchResults')
    }

    function getProfilePic(username){
        UserAccountService.getProfilePic(username)
            .then(
                function(d) {
                    vm.userProfilePic =d;
                    vm.picPath1 = '/user'+d.picPath;
                },
                function(errResponse){
                    console.error('Error while getting profilePic');
                }
            );
    }

}
