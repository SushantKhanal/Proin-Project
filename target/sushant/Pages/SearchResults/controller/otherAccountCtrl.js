angular
    .module('myApp')
    .controller('OtherAccountCtrl', otherAccountController);

otherAccountController.$inject = ['$location', 'UserAccountService'];

function otherAccountController($location, UserAccountService) {
    var vm = this;

    vm.backToSearch = backToSearch;

    vm.picPath1 = '';

    var localUserData = localStorage['localOtherUser'];

    if(localUserData !== undefined) {
        vm.user = JSON.parse(localUserData);
        getProfilePic(vm.user.username);
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
